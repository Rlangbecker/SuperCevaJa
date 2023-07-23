package com.br.supercevaja.Super.CevaJa.dto.usuarioDto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UsuarioCreateDto {

    @NotBlank(message = "O campo NOME não deve ser vazio ou nulo.")
    @Schema(description = "Nome do usuário", example = "Ricardo")
    private String nome;

    @Schema(description = "Sobreome do usuário", example = "Langbecker")
    private String sobrenome;
    @Schema(description = "CPF do usuário, digitando apenas números", example = "08870520135")
    private String cpf;
    @Schema(description = "Data de nascimento do usuário, formato YY/MM/DD", example = "1992-07-03")
    private LocalDate dataNascimento;

    @Schema(description = "Username que o usuario usará para acessar o sistema", example = "rlangbecker")
    private String username;

    @Schema(description = "Senha que o usuario usará junto ao USERNAME para acessar o sistema", example = "Abcd1234")
    private String senha;
}
