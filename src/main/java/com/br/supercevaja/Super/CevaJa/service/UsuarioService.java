package com.br.supercevaja.Super.CevaJa.service;


import com.br.supercevaja.Super.CevaJa.dto.UsuarioCreateDto;
import com.br.supercevaja.Super.CevaJa.dto.UsuarioDto;
import com.br.supercevaja.Super.CevaJa.model.Usuario;
import com.br.supercevaja.Super.CevaJa.repository.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
        Optional<Usuario> usuario = usuarioRepository.findByUsername(username);
        if(usuario.isPresent()){
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


}
