package com.security.rest;

import com.security.exception.SenhaInvalidaException;
import com.security.model.entity.Usuario;
import com.security.rest.DTO.CredenciaisDTO;
import com.security.rest.DTO.TokenDTO;
import com.security.security.JwtService;
import com.security.service.impl.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class SecurityController {

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @Autowired
    private JwtService jwtService;

    @GetMapping("/listar")
    public List<String> listar(){
        List<String> lista = new ArrayList<>();
        lista.add("Leandro");
        lista.add("Thiago");
        lista.add("Joao");

        return lista;
    }

    @GetMapping("/buscar")
    public List<String> buscar(){
        List<String> lista = new ArrayList<>();
        lista.add("Leandro");
        lista.add("Thiago");
        lista.add("Joao");

        return lista;
    }

    @PostMapping("/auth")
    public TokenDTO autenticar(@RequestBody CredenciaisDTO credenciaisDTO) {
        try {
            Usuario usuario = Usuario.builder()
                    .login(credenciaisDTO.getLogin())
                    .senha(credenciaisDTO.getSenha()).build();
            UserDetails usuarioAutenticado = usuarioService.autenticar(usuario);
            String token = jwtService.gerarToken(usuario);
            return new TokenDTO(usuario.getLogin(), token);
        } catch (UsernameNotFoundException | SenhaInvalidaException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

}
