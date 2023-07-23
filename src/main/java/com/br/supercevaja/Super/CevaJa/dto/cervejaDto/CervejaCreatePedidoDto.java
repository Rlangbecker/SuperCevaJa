package com.br.supercevaja.Super.CevaJa.dto.cervejaDto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CervejaCreatePedidoDto {
    @NotBlank(message = "O campo nome não deve ser vazio ou nulo.")
    @Schema(description = "Nome da cerveja",example = "PILSEN")
    private String nome;

    @Schema(description = "Quantidade de cervejas",example = "10")
    @Min(0)
    private Integer quantidade;
}
