package com.security.model.repository;

import com.security.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository <Usuario, Integer>{

    Optional<Usuario> findByLogin(String login);
    Optional<Usuario> findBySenha(String login);

}
