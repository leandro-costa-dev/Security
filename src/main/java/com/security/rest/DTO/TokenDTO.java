package com.security.rest.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenDTO {
    @NotEmpty(message = "Campo de login obrigatorio")
    String login;

    @NotEmpty(message = "Campo de token obrigatorio")
    String token;
}
