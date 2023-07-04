package com.br.supercevaja.Super.CevaJa.service;


import com.br.supercevaja.Super.CevaJa.dto.UsuarioCreateDto;
import com.br.supercevaja.Super.CevaJa.dto.UsuarioDto;
import com.br.supercevaja.Super.CevaJa.model.Usuario;
import com.br.supercevaja.Super.CevaJa.repository.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final ObjectMapper objectMapper;

    public UsuarioDto criarUsuario(UsuarioCreateDto usuarioCreateDto) {
        Usuario usuarioEntrada = objectMapper.convertValue(usuarioCreateDto, Usuario.class);
        Usuario usuarioRetorno = usuarioRepository.save(usuarioEntrada);
        return objectMapper.convertValue(usuarioRetorno, UsuarioDto.class);
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
                        .orElseThrow(()-> new Exception("Usuario não encontrado!"));
        usuarioRepository.deleteById(id);
    }
}
