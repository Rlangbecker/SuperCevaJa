package com.br.supercevaja.Super.CevaJa.dto.pedidoDto;

import com.br.supercevaja.Super.CevaJa.dto.cervejaDto.CervejaDto;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class PedidoDto {
    private String usernameUsuario;
    private List<CervejaDto> cervejas;

    private BigDecimal valorFinal;
}
