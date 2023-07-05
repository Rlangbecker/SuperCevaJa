//package com.br.supercevaja.Super.CevaJa.service;
//
//import com.br.supercevaja.Super.CevaJa.dto.CervejaCreatePedidoDto;
//import com.br.supercevaja.Super.CevaJa.dto.PedidoCreateDto;
//import com.br.supercevaja.Super.CevaJa.model.Cerveja;
//import com.br.supercevaja.Super.CevaJa.model.Pedido;
//import com.br.supercevaja.Super.CevaJa.model.Usuario;
//import com.br.supercevaja.Super.CevaJa.repository.PedidoRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//@RequiredArgsConstructor
//public class PedidoService {
//
//    private final PedidoRepository pedidoRepository;
//
//    private final UsuarioService usuarioService;
//
//    private final CervejaService cervejaService;
//
//    public Pedido criarPedido(PedidoCreateDto pedidoCreateDto) throws Exception {
//       Boolean usuarioExiste = usuarioService.buscarPorUsername(pedidoCreateDto.getUsernameUsuario();
//        if (existe)) {
//            throw new Exception("Username não consta no banco!");
//        }
//
//        List<Cerveja> cervejas = new ArrayList<>();
//        BigDecimal valor = new BigDecimal(0);
//
//        pedidoCreateDto.getCervejaCreatePedidoDtos().stream()
//                .map(cervejaCreatePedidoDto -> {
//                    try {
//                        Cerveja cerveja = cervejaService.buscarPorNome(cervejaCreatePedidoDto.getNome());
//                       cerveja.setValor(retornarValorComDesconto(cervejaCreatePedidoDto));
//                        cervejas.add(cerveja);
//                        return cervejas;
//                    } catch (Exception e) {
//                        System.out.println(e.getMessage());
//                    }
//                    return cervejas;
//                }).collect(Collectors.toList());
//
//
//        return pedidoRepository.save(pedido);
//    }
//
//    public BigDecimal retornarValorComDesconto(CervejaCreatePedidoDto cervejaCreatePedidoDto) throws Exception {
//        BigDecimal valorTotal = cervejaService.calcularValorTotal(cervejaCreatePedidoDto);
//        if(cervejaCreatePedidoDto.getQuantidade()>10){
//            valorTotal.divide(new BigDecimal(100)).multiply(new BigDecimal(10));
//        }
//        return valorTotal;
//    }
//}
