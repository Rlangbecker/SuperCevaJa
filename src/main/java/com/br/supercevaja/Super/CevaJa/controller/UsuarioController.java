package com.br.supercevaja.Super.CevaJa.controller;

import com.br.supercevaja.Super.CevaJa.controller.documentationInterface.UsuarioControllerInterface;
import com.br.supercevaja.Super.CevaJa.dto.usuarioDto.UsuarioCreateDto;
import com.br.supercevaja.Super.CevaJa.dto.usuarioDto.UsuarioDto;
import com.br.supercevaja.Super.CevaJa.dto.usuarioDto.UsuarioEditDto;
import com.br.supercevaja.Super.CevaJa.exception.RegraDeNegocioException;
import com.br.supercevaja.Super.CevaJa.service.UsuarioService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/supercevaja/api/v1/usuarios")
public class UsuarioController implements UsuarioControllerInterface {

    private final UsuarioService usuarioService;


    @PostMapping
    public ResponseEntity<UsuarioDto> criarUsuario(@RequestBody @Valid UsuarioCreateDto usuarioCreateDto) throws RegraDeNegocioException {
        return new ResponseEntity<>(usuarioService.criarUsuario(usuarioCreateDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDto>> buscarTodosUsuario(){
        return new ResponseEntity<>(usuarioService.buscarTodosUsuarios(),HttpStatus.OK);
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<UsuarioDto> buscarPorId(@PathParam("idUsuario") Integer id) throws RegraDeNegocioException {
        return new ResponseEntity<>(usuarioService.buscarUsuarioPorId(id), HttpStatus.OK);
    }

    @PutMapping("/{idUsuario}")
    public ResponseEntity<UsuarioDto> editarPorId(@PathParam("idUsuario") Integer idUsuario, @RequestBody UsuarioEditDto usuarioEditDto) throws RegraDeNegocioException {
        return new ResponseEntity<>(usuarioService.alterarPorUserId(idUsuario,usuarioEditDto),HttpStatus.OK);
    }

    @DeleteMapping("/{username}")
    public ResponseEntity deletar(@PathVariable("username") String username) throws RegraDeNegocioException {
        usuarioService.deletarPorUsername(username);
        return ResponseEntity.accepted().build();
    }
}
