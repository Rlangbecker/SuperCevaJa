package com.br.supercevaja.Super.CevaJa.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class TokenAuthenticationFilter extends OncePerRequestFilter {
    private final JwtUtils jwtUtils;
    private final String BEARER = "Bearer ";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String tokenFromHeader = getTokenFromHeader(request);
        UsernamePasswordAuthenticationToken user = jwtUtils.validateToken(tokenFromHeader);
        SecurityContextHolder.getContext().setAuthentication(user);
        filterChain.doFilter(request, response);
    }

//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//            throws ServletException, IOException {
//        String tokenFromHeader = getTokenFromHeader(request);
//        if (tokenFromHeader != null) {
//            Authentication authentication = jwtUtils.validateToken(tokenFromHeader);
//            if (authentication != null) {
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//            }
//        }
//        filterChain.doFilter(request, response);
//    }


    private String getTokenFromHeader(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token == null) {
            return null;
        }
        return token.replace(BEARER, "");
    }
}