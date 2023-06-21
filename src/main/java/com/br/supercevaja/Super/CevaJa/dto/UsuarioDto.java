package com.br.supercevaja.Super.CevaJa.dto;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record UsuarioDto(
        Integer idUsuario,
        String username,
        LocalDate dataNascimento
) {
}
