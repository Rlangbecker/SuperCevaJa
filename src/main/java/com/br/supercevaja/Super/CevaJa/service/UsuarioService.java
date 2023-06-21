package com.br.supercevaja.Super.CevaJa.service;


import com.br.supercevaja.Super.CevaJa.dto.UsuarioDto;
import com.br.supercevaja.Super.CevaJa.model.Usuario;
import com.br.supercevaja.Super.CevaJa.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public Usuario criarUsuario(Usuario usuario){
        return usuarioRepository.save(usuario);
    }
    public Optional<Usuario> buscarUsuarioPorId(Integer id) {
        if (id == 0) {
            throw new RuntimeException("Id inexistente");
        }
        return usuarioRepository.findById(id);
    }
//    public Usuario alterarPorId(Usuario usuario) {
//        if (usuario == null) {
//            throw new RuntimeException("Sem alterações");
//        }
//        usuarioRepository.save(usuario);
//    }

    public Usuario alterarPorUserName(UsuarioDto usuarioDto) {
        Usuario usuarioParaAlterar = usuarioRepository.findByUsername(usuarioDto.username());
        Usuario usuario = new Usuario();
        usuario.setUsername(usuarioDto.username());
        usuario.setDataNascimento(usuarioDto.dataNascimento());
        return usuarioRepository.save(usuario);
    }
    public void deletarPorId(Integer id) {
        usuarioRepository.deleteById(id);
    }
}
