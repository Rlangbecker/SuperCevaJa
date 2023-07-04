package com.br.supercevaja.Super.CevaJa.dto;

import com.br.supercevaja.Super.CevaJa.model.enums.TipoCeva;
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