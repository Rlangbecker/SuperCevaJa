package com.br.supercevaja.Super.CevaJa.controller;

import com.br.supercevaja.Super.CevaJa.controller.documentationInterface.PedidoControllerInterface;
import com.br.supercevaja.Super.CevaJa.dto.pedidoDto.PedidoCreateDto;
import com.br.supercevaja.Super.CevaJa.dto.pedidoDto.PedidoDto;
import com.br.supercevaja.Super.CevaJa.exception.RegraDeNegocioException;
import com.br.supercevaja.Super.CevaJa.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/supercevaja/api/v1/pedidos")
public class PedidoController implements PedidoControllerInterface {

    private final PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<PedidoDto> criarPedido(@RequestBody PedidoCreateDto pedido) throws RegraDeNegocioException {
        return new ResponseEntity<>(pedidoService.criarPedido(pedido), HttpStatus.CREATED);
    }


}
