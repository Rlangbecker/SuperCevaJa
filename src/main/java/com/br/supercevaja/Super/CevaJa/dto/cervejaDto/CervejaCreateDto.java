package com.br.supercevaja.Super.CevaJa.dto.cervejaDto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CervejaCreateDto {

    private String nome;

    private BigDecimal valor;

    private Integer quantidade;
}
