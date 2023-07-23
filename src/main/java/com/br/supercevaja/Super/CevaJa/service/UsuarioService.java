package com.br.supercevaja.Super.CevaJa.service;


import com.br.supercevaja.Super.CevaJa.dto.usuarioDto.UsuarioCreateDto;
import com.br.supercevaja.Super.CevaJa.dto.usuarioDto.UsuarioDto;
import com.br.supercevaja.Super.CevaJa.dto.usuarioDto.UsuarioEditDto;
import com.br.supercevaja.Super.CevaJa.exception.RegraDeNegocioException;
import com.br.supercevaja.Super.CevaJa.model.Cargo;
import com.br.supercevaja.Super.CevaJa.model.Usuario;
import com.br.supercevaja.Super.CevaJa.repository.CargoRepository;
import com.br.supercevaja.Super.CevaJa.repository.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepository;

    private final CargoRepository cargoRepository;
    private final ObjectMapper objectMapper;

    private final Integer ROLE_USER = 1;


    public UsuarioDto criarUsuario(UsuarioCreateDto usuarioCreateDto) throws RegraDeNegocioException {

        buscarPorUsername(usuarioCreateDto.getUsername());

        Usuario usuarioEntrada = objectMapper.convertValue(usuarioCreateDto, Usuario.class);
        usuarioEntrada.setAtivo(true);
        usuarioEntrada.setSenha(passwordEncoder.encode(usuarioCreateDto.getSenha()));
        usuarioEntrada.setCargo(cargoRepository.findById(ROLE_USER).get());
        Usuario usuarioRetorno = usuarioRepository.save(usuarioEntrada);
        return objectMapper.convertValue(usuarioRetorno, UsuarioDto.class);
    }

    private Boolean buscarPorUsername(String username) {
        return usuarioRepository.findByUsername(username).isPresent();
    }

    public List<UsuarioDto> buscarTodosUsuarios() {
        return usuarioRepository.findAll().stream()
                .map(usuario -> objectMapper.convertValue(usuario, UsuarioDto.class))
                .collect(Collectors.toList());
    }

    public UsuarioDto buscarUsuarioPorId(Integer id) throws RegraDeNegocioException {
        Usuario usuarioRetorno = usuarioRepository.findById(id)
                .orElseThrow(() -> new RegraDeNegocioException("Usuario não encontrado!"));
        return objectMapper.convertValue(usuarioRetorno, UsuarioDto.class);
    }

    public UsuarioDto alterarPorUserId(Integer idUsuario, UsuarioEditDto usuarioEditDto) throws RegraDeNegocioException {
        Usuario usuarioRetorno = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RegraDeNegocioException("Usuario não encotrado por este ID"));

        usuarioRetorno.setNome(usuarioEditDto.getNome());
        usuarioRetorno.setSobrenome(usuarioEditDto.getSobrenome());

        return objectMapper.convertValue(usuarioRepository.save(usuarioRetorno), UsuarioDto.class);
    }

    public void deletarPorId(Integer id) throws RegraDeNegocioException {
        usuarioRepository.findById(id)
                .orElseThrow(() -> new RegraDeNegocioException("Usuario não encontrado!"));
        usuarioRepository.deleteById(id);
    }


    public void deletarPorUsername(String username) throws RegraDeNegocioException {
        Usuario usuarioRetorno = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new RegraDeNegocioException("Usuario com username " + username + " não encontrado!"));
        usuarioRetorno.setAtivo(false);
        usuarioRepository.save(usuarioRetorno);

    }

    public UsuarioDto validarUsuario(String username) throws RegraDeNegocioException {
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new RegraDeNegocioException("Usuario com este username não encontrado!"));

        Long idade = ChronoUnit.YEARS.between(LocalDate.now(), usuario.getDataNascimento());
        if (idade > 18) {
            throw new RegraDeNegocioException("Não é possível adicionar um pedido para um Usuário menor de idade!");
        }
        return objectMapper.convertValue(usuario, UsuarioDto.class);
    }

    public Usuario buscarUsuarioPorUsername(String username) throws RegraDeNegocioException {
        return usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new RegraDeNegocioException("Usuario com este username não encontrado!"));
    }

}

