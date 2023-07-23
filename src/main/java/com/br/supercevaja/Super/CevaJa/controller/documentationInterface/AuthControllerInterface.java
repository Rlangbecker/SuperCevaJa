package com.br.supercevaja.Super.CevaJa.controller.documentationInterface;

import com.br.supercevaja.Super.CevaJa.dto.loginDto.LoginDto;
import com.br.supercevaja.Super.CevaJa.exception.RegraDeNegocioException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.nio.file.AccessDeniedException;
import java.security.NoSuchAlgorithmException;

public interface AuthControllerInterface {

    @Operation(summary = "Fazer Login", description = "Inserindo Username e Senha válidos, retorna um token para autenticação e acesso aos endpoints")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Token gerado"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    ResponseEntity<String> logar(@RequestBody LoginDto loginDto) throws RegraDeNegocioException, NoSuchAlgorithmException, AccessDeniedException;
}
