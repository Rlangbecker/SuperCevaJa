package com.br.supercevaja.Super.CevaJa.controller.documentationInterface;

import com.br.supercevaja.Super.CevaJa.dto.cervejaDto.CervejaCreateDto;
import com.br.supercevaja.Super.CevaJa.dto.cervejaDto.CervejaDto;
import com.br.supercevaja.Super.CevaJa.exception.RegraDeNegocioException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface CervejaControllerInterface {
    @Operation(summary = "Cadastrar nova cerveja", description = "Cadastra nova cerveja no banco de dados")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Criação de cerveja aceita!"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    ResponseEntity<CervejaDto> cadastrarCerveja(@RequestBody CervejaCreateDto cervejaCreateDto) throws RegraDeNegocioException;

    @Operation(summary = "Lista todos os tipos de cerveja", description = "Listar todos os tipos de cerveja do banco de dados")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna a lista de cervejas"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    ResponseEntity<List<CervejaDto>> retornarTiposCeva();


    @Operation(summary = "Editar cerveja por nome", description = "Edita a cerveja  e salva informaçõs no banco de dados pelo Nome informado")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Edição de cerveja feita"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    ResponseEntity<CervejaDto> alterarPorNome(@PathVariable("nomeCerveja") String nomeCerveja, @RequestBody CervejaCreateDto cervejaCreateDto) throws RegraDeNegocioException;

    @Operation(summary = "Deletar cerveja", description = "Deleta a cerveja no banco de dados pelo Nome informado")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Delete de cerveja feito"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    ResponseEntity deletar(@PathVariable("nomeCerveja") String nomeCerveja) throws Exception;

    @Operation(summary = "Retorna cerveja pelo Id", description = "Retorna a cerveja no banco de dados pelo Id informado")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna uma cerveja"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    ResponseEntity<CervejaDto> buscarPorId(@PathVariable("idCerveja") Integer id) throws RegraDeNegocioException;
}
