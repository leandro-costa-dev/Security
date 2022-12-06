package com.security;

import com.security.model.entity.Usuario;
import com.security.security.JwtService;
import com.security.service.impl.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.sound.midi.Soundbank;

@SpringBootApplication
public class SecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class, args);
    }

    @Autowired
    private UsuarioServiceImpl usuarioServiceImpl;

    @Bean
    public CommandLineRunner executar() {
        return args -> {
            System.out.println("Aplicação iniciada");
            Usuario usuario = new Usuario();
            usuario.setLogin("leandro");
            usuario.setSenha("123");

            Usuario novo = usuarioServiceImpl.SalvarUsuario(usuario);

            System.out.println(novo.toString());


        };
    }
}
