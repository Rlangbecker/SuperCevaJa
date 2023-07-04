package com.br.supercevaja.Super.CevaJa.controller;

import com.br.supercevaja.Super.CevaJa.dto.UsuarioCreateDto;
import com.br.supercevaja.Super.CevaJa.dto.UsuarioDto;
import com.br.supercevaja.Super.CevaJa.model.Usuario;
import com.br.supercevaja.Super.CevaJa.service.UsuarioService;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/supercevaja/api/v1/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;


    @PostMapping
    public ResponseEntity<UsuarioCreateDto> criarUsuario(@RequestBody UsuarioCreateDto usuarioCreateDto) {
        return new ResponseEntity<>(usuarioService.criarUsuario(usuarioCreateDto), HttpStatus.OK);
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<UsuarioDto> buscarPorId(@PathParam("idUsuario") Integer id) throws Exception {
        return new ResponseEntity<>(usuarioService.buscarUsuarioPorId(id), HttpStatus.OK);
    }

    @PutMapping("/{idUsuario}")
    public ResponseEntity<UsuarioDto> editarPorId(@PathParam("idUsuario") Integer idUsuario, @RequestBody UsuarioCreateDto usuarioCreateDto) throws Exception {
        return new ResponseEntity<>(usuarioService.alterarPorUserId(idUsuario,usuarioCreateDto),HttpStatus.OK);
    }

    @DeleteMapping("/{idUsuario}")
    public ResponseEntity deletar(@PathVariable("idUsuario") Integer id) throws Exception {
        usuarioService.deletarPorId(id);
        return ResponseEntity.accepted().build();
    }
}
