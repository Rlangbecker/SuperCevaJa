package com.br.supercevaja.Super.CevaJa.service;

import com.br.supercevaja.Super.CevaJa.model.Pedido;
import com.br.supercevaja.Super.CevaJa.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    public Pedido criarPedido(Pedido pedido){
        return pedidoRepository.save(pedido);
    }
}
