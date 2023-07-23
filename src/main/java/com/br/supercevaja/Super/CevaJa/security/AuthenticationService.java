package com.br.supercevaja.Super.CevaJa.security;

import com.br.supercevaja.Super.CevaJa.exception.RegraDeNegocioException;
import com.br.supercevaja.Super.CevaJa.model.Usuario;
import com.br.supercevaja.Super.CevaJa.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements UserDetailsService {
    private final UsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        try {
            Usuario usuarioEntityOptional = usuarioService.buscarUsuarioPorUsername(username);
            return (UserDetails) usuarioEntityOptional;
        } catch (RegraDeNegocioException e) {
            throw new RuntimeException(e);
        }

    }
}