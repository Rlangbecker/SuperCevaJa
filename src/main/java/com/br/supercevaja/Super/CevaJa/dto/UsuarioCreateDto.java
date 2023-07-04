package com.br.supercevaja.Super.CevaJa.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UsuarioCreateDto {
    private String username;
    private LocalDate dataNascimento;
}
