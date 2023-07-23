package com.br.supercevaja.Super.CevaJa.dto.loginDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class LoginDto {
    @Schema(description = "Username do usuário", example = "rlangbecker")
    private String username;
    @Schema(description = "Senha do usuário", example = "Abcd1234")
    private String senha;
}
