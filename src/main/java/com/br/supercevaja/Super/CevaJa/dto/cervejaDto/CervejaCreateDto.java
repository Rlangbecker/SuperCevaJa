package com.br.supercevaja.Super.CevaJa.dto.cervejaDto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CervejaCreateDto {

    @NotBlank(message = "O campo NOME não deve ser vazio ou nulo.")
    @Schema(description = "Nome da cerveja",example = "Novo nome")
    private String nome;

    @Schema(description = "Valor da cerveja")
    @Min(0)
    private BigDecimal valor;

    private Integer quantidade;
}
