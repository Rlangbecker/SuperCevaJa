package com.br.supercevaja.Super.CevaJa.controller.documentationInterface;

import com.br.supercevaja.Super.CevaJa.dto.pedidoDto.PedidoCreateDto;
import com.br.supercevaja.Super.CevaJa.dto.pedidoDto.PedidoDto;
import com.br.supercevaja.Super.CevaJa.exception.RegraDeNegocioException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface PedidoControllerInterface {
    @Operation(summary = "Cria um novo pedido", description = "Cadastra um novo pedido no banco de dados")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Cadastro de pedido aceito"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    ResponseEntity<PedidoDto> criarPedido(@RequestBody PedidoCreateDto pedido) throws RegraDeNegocioException;

}
