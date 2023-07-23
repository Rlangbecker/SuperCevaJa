package com.br.supercevaja.Super.CevaJa.dto.usuarioDto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UsuarioDto {
    private Integer idUsuario;
    private String username;
    private String nome;

    private String sobrenome;
    private String cpf;
    private LocalDate dataNascimento;


}
