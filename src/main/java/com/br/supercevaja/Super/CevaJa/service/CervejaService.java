package com.br.supercevaja.Super.CevaJa.service;

import com.br.supercevaja.Super.CevaJa.dto.cervejaDto.CervejaCreateDto;
import com.br.supercevaja.Super.CevaJa.dto.cervejaDto.CervejaCreatePedidoDto;
import com.br.supercevaja.Super.CevaJa.dto.cervejaDto.CervejaDto;
import com.br.supercevaja.Super.CevaJa.exception.RegraDeNegocioException;
import com.br.supercevaja.Super.CevaJa.model.Cerveja;
import com.br.supercevaja.Super.CevaJa.repository.CervejaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CervejaService {

    private final CervejaRepository cervejaRepository;
    private final ObjectMapper objectMapper;


    public CervejaDto cadastrarCerveja(CervejaCreateDto cervejaCreateDto) throws RegraDeNegocioException {

        if (buscarPorNome(cervejaCreateDto.getNome())) {
            throw new RegraDeNegocioException("Cerveja já cadastrada no sistema!");
        }
        Cerveja cerveja = objectMapper.convertValue(cervejaCreateDto, Cerveja.class);
        cerveja.setAtivo(true);
        Long novoId = cervejaRepository.count() + 1l;
        cerveja.setIdCerveja(novoId.intValue());
        Cerveja cervejaReturn = cervejaRepository.save(cerveja);
        CervejaDto cervejaDtoRetorno = objectMapper.convertValue(cervejaReturn, CervejaDto.class);
        return cervejaDtoRetorno;
    }

    private Boolean buscarPorNome(String nomeCerveja) throws RegraDeNegocioException {
        return cervejaRepository.findCervejaByNome(nomeCerveja).isPresent();
    }

    public CervejaDto buscarPorNomeDto(String nomeCerveja) throws RegraDeNegocioException {
        Cerveja cerveja = cervejaRepository.findCervejaByNome(nomeCerveja)
                .orElseThrow(() -> new RegraDeNegocioException("Cerveja com este nome não consta no banco!"));
        return objectMapper.convertValue(cerveja, CervejaDto.class);
    }

    public BigDecimal calcularValorTotal(CervejaCreatePedidoDto cervejaCreatePedidoDto) throws RegraDeNegocioException {
        Cerveja cerveja = cervejaRepository.findCervejaByNome(cervejaCreatePedidoDto.getNome())
                .orElseThrow(() -> new RegraDeNegocioException("Cerveja não cadastrada no sistema: " + cervejaCreatePedidoDto.getNome()));

        BigDecimal valorTotal = BigDecimal.valueOf(cervejaCreatePedidoDto.getQuantidade())
                .multiply(cerveja.getValor());
        return valorTotal;
    }


    public CervejaDto buscarPorId(Integer id) throws RegraDeNegocioException {
        Cerveja cervejaReturn = cervejaRepository.findById(id).
                orElseThrow(() -> new RegraDeNegocioException("Id não encontrado"));
        CervejaDto cervejaDtoRetorno = objectMapper.convertValue(cervejaReturn, CervejaDto.class);
        return cervejaDtoRetorno;
    }

    public CervejaDto alterarPorNome(String nomeCerveja, CervejaCreateDto cervejaCreateDto) throws RegraDeNegocioException {
        Cerveja cerveja = cervejaRepository.findCervejaByNome(nomeCerveja).orElseThrow(() -> new RegraDeNegocioException("Cerveja com o nome " + nomeCerveja + " não encontrado"));
        cerveja.setNome(cervejaCreateDto.getNome());
        cerveja.setValor(cervejaCreateDto.getValor());
        cerveja.setQuantidade(cervejaCreateDto.getQuantidade());
        CervejaDto cevaReturn = objectMapper.convertValue(cervejaRepository.save(cerveja), CervejaDto.class);
        return cevaReturn;
    }

    public void deletarPorNome(String nomeCerveja) throws RegraDeNegocioException {
        Cerveja cervejaRetorno = cervejaRepository.findCervejaByNome(nomeCerveja)
                .orElseThrow(() -> new RegraDeNegocioException("Cerveja com o nome " + nomeCerveja + " não encontrado"));
        cervejaRetorno.setAtivo(false);
        cervejaRepository.save(cervejaRetorno);
    }


    public List<CervejaDto> retornarTiposCerveja() {
        return cervejaRepository.findAll().stream()
                .map(cerveja -> objectMapper.convertValue(cerveja, CervejaDto.class))
                .collect(Collectors.toList());
    }

}
