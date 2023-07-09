package com.br.supercevaja.Super.CevaJa.service;

import com.br.supercevaja.Super.CevaJa.dto.cervejaDto.CervejaCreatePedidoDto;
import com.br.supercevaja.Super.CevaJa.dto.cervejaDto.CervejaDto;
import com.br.supercevaja.Super.CevaJa.dto.pedidoDto.PedidoCreateDto;
import com.br.supercevaja.Super.CevaJa.dto.pedidoDto.PedidoDto;
import com.br.supercevaja.Super.CevaJa.dto.usuarioDto.UsuarioDto;
import com.br.supercevaja.Super.CevaJa.exception.RegraDeNegocioException;
import com.br.supercevaja.Super.CevaJa.model.Cerveja;
import com.br.supercevaja.Super.CevaJa.model.Pedido;
import com.br.supercevaja.Super.CevaJa.model.Usuario;
import com.br.supercevaja.Super.CevaJa.model.integration.TempsResponse;
import com.br.supercevaja.Super.CevaJa.repository.PedidoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    private final UsuarioService usuarioService;

    private final CervejaService cervejaService;

    private final WeatherIntegrationService weatherIntegrationService;
    private final ObjectMapper objectMapper;

    public PedidoDto criarPedido(PedidoCreateDto pedidoCreateDto) throws RegraDeNegocioException {

        List<BigDecimal> valores = new ArrayList<>();

        UsuarioDto usuarioDto = usuarioService.validarUsuario(pedidoCreateDto.getUsernameUsuario());

        Usuario usuarioRetorno = objectMapper.convertValue(usuarioDto, Usuario.class);

        pedidoCreateDto.getCervejas().stream()
                .forEach(cerveja -> {
                    try {
                        valores.add(retornarValorComDesconto(cerveja));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });

        Pedido pedido = objectMapper.convertValue(pedidoCreateDto, Pedido.class);

        pedido.setUsuario(usuarioRetorno);

        BigDecimal soma = valores.stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        pedido.setValorFinal(calcularDescontoPorTemperaturaAtual(soma));

        PedidoDto pedidoDto = objectMapper.convertValue(pedidoRepository.save(pedido), PedidoDto.class);

        List<CervejaDto> cervejasDto = pedidoCreateDto.getCervejas().stream()
                .map(cerveja -> {
                    try {
                        CervejaDto cervejaDto = cervejaService.buscarPorNomeDto(cerveja.getNome());
                        cervejaDto.setQuantidade(cerveja.getQuantidade());
                        return cervejaDto;
                    } catch (RegraDeNegocioException e) {
                        throw new RuntimeException(e);
                    }
                }).collect(Collectors.toList());

        pedidoDto.setCervejas(cervejasDto);
        pedidoDto.setUsernameUsuario(pedidoCreateDto.getUsernameUsuario());

        return pedidoDto;
    }

    public BigDecimal retornarValorComDesconto(CervejaCreatePedidoDto cervejaCreatePedidoDto) throws Exception {
        BigDecimal valorTotal = cervejaService.calcularValorTotal(cervejaCreatePedidoDto);
        if (cervejaCreatePedidoDto.getQuantidade() > 10) {
            BigDecimal valorTemporario = valorTotal.divide(BigDecimal.valueOf(100)).multiply(BigDecimal.valueOf(10));
            valorTotal = valorTotal.subtract(valorTemporario);
            return valorTotal;
        } else {
            return valorTotal;
        }
    }

    public BigDecimal calcularDescontoPorTemperaturaAtual(BigDecimal valor) {
        TempsResponse tempsResponse = weatherIntegrationService.buscarTemperaturaAtual();
        if (tempsResponse.getTemp_c() <= 22) {
            valor = valor.subtract(valor.divide(new BigDecimal(100)).multiply(new BigDecimal(15)));
            return valor;
        } else {
            return valor;
        }
    }
}
