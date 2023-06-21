package com.br.supercevaja.Super.CevaJa.controller;

import com.br.supercevaja.Super.CevaJa.dto.UsuarioDto;
import com.br.supercevaja.Super.CevaJa.model.Usuario;
import com.br.supercevaja.Super.CevaJa.service.UsuarioService;
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
    public ResponseEntity<Usuario> criarUsuario(@RequestBody Usuario usuario){
        return new ResponseEntity<>(usuarioService.criarUsuario(usuario), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public Optional<Usuario> buscarPorId(@PathVariable("id") Integer id) {
        return usuarioService.buscarUsuarioPorId(id);
    }
    @PutMapping
    public ResponseEntity<UsuarioDto> editar(@RequestBody UsuarioDto usuarioDto) {
        usuarioService.alterarPorUserName(usuarioDto);
        return null;
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable("id") Integer id) {
        usuarioService.deletarPorId(id);
        return ResponseEntity.accepted().build();
    }
}
