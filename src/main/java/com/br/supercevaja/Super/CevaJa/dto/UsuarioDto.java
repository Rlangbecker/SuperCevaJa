package com.br.supercevaja.Super.CevaJa.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UsuarioDto extends UsuarioCreateDto{
    private Integer idUsuario;
}
