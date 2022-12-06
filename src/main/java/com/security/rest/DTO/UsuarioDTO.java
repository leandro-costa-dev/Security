package com.security.rest.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {

    @NotEmpty(message = "Campo de login obrigatorio")
    String login;
    @NotEmpty(message = "Campo de senha obrigatorio")
    String senha;
    @NotEmpty(message = "Campo de permissao obrigatorio")
    String permissao;

}
