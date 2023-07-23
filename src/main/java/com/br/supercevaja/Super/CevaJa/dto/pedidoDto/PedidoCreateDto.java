package com.br.supercevaja.Super.CevaJa.dto.pedidoDto;

import com.br.supercevaja.Super.CevaJa.dto.cervejaDto.CervejaCreatePedidoDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class PedidoCreateDto {
    @NotBlank(message = "O campo Username não deve ser vazio ou nulo.")
    @Schema(description = "Username do clliente",example = "rlangbecker")
    private String usernameUsuario;

    @NotNull
    private List<CervejaCreatePedidoDto> cervejas;
}
