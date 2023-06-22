package com.br.supercevaja.Super.CevaJa.dto;

import java.time.LocalDate;

public record UsuarioCreateDto(
        String username,
        LocalDate dataNascimento
) {
}
