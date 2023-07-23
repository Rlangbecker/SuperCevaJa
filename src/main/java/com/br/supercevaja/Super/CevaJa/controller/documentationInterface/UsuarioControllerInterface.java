package com.br.supercevaja.Super.CevaJa.controller.documentationInterface;

import com.br.supercevaja.Super.CevaJa.dto.usuarioDto.UsuarioCreateDto;
import com.br.supercevaja.Super.CevaJa.dto.usuarioDto.UsuarioDto;
import com.br.supercevaja.Super.CevaJa.dto.usuarioDto.UsuarioEditDto;
import com.br.supercevaja.Super.CevaJa.exception.RegraDeNegocioException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface UsuarioControllerInterface {
    @Operation(summary = "Cadastrar novo usuario", description = "Cadastra o usuário no banco de dados")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Criação de cliente aceita!"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    ResponseEntity<UsuarioDto> criarUsuario(@RequestBody @Valid UsuarioCreateDto usuarioCreateDto) throws RegraDeNegocioException;

    @Operation(summary = "Lista todos os usuários", description = "Listar todos os usuarios do banco de dados")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna a lista de clientes"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    ResponseEntity<List<UsuarioDto>> buscarTodosUsuario();

    @Operation(summary = "Retorna usuário por ID", description = "Retorna o usuário do banco de dados pelo Id informado")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna o usuário"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
     ResponseEntity<UsuarioDto> buscarPorId(@PathParam("idUsuario") Integer id) throws RegraDeNegocioException ;

    @Operation(summary = "Editar usuário", description = "Edita o usuário  e salva informaçõs no banco de dados pelo Id informado")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Edição de usuário feita"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
   ResponseEntity<UsuarioDto> editarPorId(@PathParam("idUsuario") Integer idUsuario, @RequestBody UsuarioEditDto usuarioEditDto) throws RegraDeNegocioException;

    @Operation(summary = "Deletar usuário", description = "Deleta o usuário no banco de dados pelo Username informado")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Delete de usuário feito"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    ResponseEntity deletar(@PathVariable("username") String username) throws RegraDeNegocioException ;

}
