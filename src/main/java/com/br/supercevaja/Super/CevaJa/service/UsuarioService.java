package com.br.supercevaja.Super.CevaJa.service;


import com.br.supercevaja.Super.CevaJa.dto.UsuarioCreateDto;
import com.br.supercevaja.Super.CevaJa.dto.UsuarioDto;
import com.br.supercevaja.Super.CevaJa.model.Usuario;
import com.br.supercevaja.Super.CevaJa.repository.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final ObjectMapper objectMapper;

    public UsuarioDto criarUsuario(UsuarioCreateDto usuarioCreateDto) throws Exception {
        if(buscarPorUsername(usuarioCreateDto.getUsername())){
            throw new Exception("Username já está em uso, por favor escolha outro");
        }
        Usuario usuarioEntrada = objectMapper.convertValue(usuarioCreateDto, Usuario.class);
        Usuario usuarioRetorno = usuarioRepository.save(usuarioEntrada);
        return objectMapper.convertValue(usuarioRetorno, UsuarioDto.class);
    }

    public Boolean buscarPorUsername(String username) {
        if(usuarioRepository.findByUsername(username).isPresent()){
            return true;
        } else {
            return false;
        }
    }



    public UsuarioDto buscarUsuarioPorId(Integer id) throws Exception {
        Usuario usuarioRetorno = usuarioRepository.findById(id)
                .orElseThrow(() -> new Exception("Usuario não encontrado!"));
        return objectMapper.convertValue(usuarioRetorno, UsuarioDto.class);
    }

    public UsuarioDto alterarPorUserId(Integer idUsuario, UsuarioCreateDto usuarioCreateDto) throws Exception {
        Usuario usuarioRetorno = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new Exception("Usuario não encotrado por este ID"));

        usuarioRetorno.setUsername(usuarioCreateDto.getUsername());

        usuarioRetorno.setDataNascimento(usuarioCreateDto.getDataNascimento());

        UsuarioDto usuarioDto = objectMapper.convertValue(usuarioRepository.save(usuarioRetorno), UsuarioDto.class);
        return usuarioDto;
    }

    public void deletarPorId(Integer id) throws Exception {
        usuarioRepository.findById(id)
                .orElseThrow(() -> new Exception("Usuario não encontrado!"));
        usuarioRepository.deleteById(id);
    }

    public Usuario validarUsuario(Integer idUsuario) throws Exception {
        UsuarioDto usuarioDto = buscarUsuarioPorId(idUsuario);
        if(!buscarPorUsername(usuarioDto.getUsername())){
            throw new Exception("Usuario com este username não encontrado!");
        }
        Boolean isMaior = isMaiorDeIdade(usuarioDto);
        if (!isMaior) {
            throw new RuntimeException("É menor de idade");
        }

        Usuario usuario = objectMapper.convertValue(usuarioDto, Usuario.class);
        return usuario;
    }

    public Boolean isMaiorDeIdade (UsuarioDto usuarioDto) {
        long dateDiff = ChronoUnit.YEARS.between(LocalDate.now(), usuarioDto.getDataNascimento());
        if (dateDiff < 18) {
            return false;
        }
        return true;
    }




}
