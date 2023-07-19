package com.br.supercevaja.Super.CevaJa.controller;

import com.br.supercevaja.Super.CevaJa.dto.loginDto.LoginDto;
import com.br.supercevaja.Super.CevaJa.exception.RegraDeNegocioException;
import com.br.supercevaja.Super.CevaJa.security.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.AccessDeniedException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/supercevaja/api/v1/auth")
public class AuthController {

    public final AuthenticationManager authenticationManager;

    private final JwtUtils jwtUtils;
    @PostMapping
    public String logar(@RequestBody LoginDto loginDto) throws RegraDeNegocioException, NoSuchAlgorithmException, AccessDeniedException {

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername(),
                        loginDto.getSenha()
                );

        Authentication authentication= authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        if (!userDetails.getAuthorities().stream().anyMatch(role -> role.getAuthority().equals("ROLE_USER"))) {
            throw new AccessDeniedException("Usuário não tem permissão para acessar este recurso.");
        }

      return jwtUtils.generateToken(loginDto.getUsername());
    }
}
