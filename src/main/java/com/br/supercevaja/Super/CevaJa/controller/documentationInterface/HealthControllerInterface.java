package com.br.supercevaja.Super.CevaJa.controller.documentationInterface;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;

public interface HealthControllerInterface {
    @Operation(summary = "Retorna status da aplicação", description = "Retorna uma mensagem validando que o sistema está online")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Sistema rodando"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    ResponseEntity<String> health();
}
