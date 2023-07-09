package com.br.supercevaja.Super.CevaJa.dto.pedidoDto;

import com.br.supercevaja.Super.CevaJa.dto.cervejaDto.CervejaCreatePedidoDto;
import lombok.Data;

import java.util.List;

@Data
public class PedidoCreateDto {
    private String usernameUsuario;
    private List<CervejaCreatePedidoDto> cervejas;
}
