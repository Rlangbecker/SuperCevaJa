package com.br.supercevaja.Super.CevaJa.dto;

import com.br.supercevaja.Super.CevaJa.model.Cerveja;
import lombok.Data;

import java.util.List;

@Data
public class PedidoCreateDto {
    private String usernameUsuario;
    private List<CervejaCreatePedidoDto> cervejaCreatePedidoDtos;
}
