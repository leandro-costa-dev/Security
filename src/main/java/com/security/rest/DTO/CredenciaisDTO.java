package com.security.rest.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CredenciaisDTO {

    @NotEmpty(message = "Campo login obrigatório")
    private String login;

    @NotEmpty(message = "Campo de senha obrigatorio")
    private String senha;

}
