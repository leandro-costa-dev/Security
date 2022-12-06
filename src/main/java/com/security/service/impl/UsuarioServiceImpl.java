package com.security.service.impl;

import com.security.exception.SenhaInvalidaException;
import com.security.model.entity.Usuario;
import com.security.model.repository.UsuarioRepository;
import com.security.rest.DTO.UsuarioDTO;
import com.security.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service

public class UsuarioServiceImpl implements UsuarioService, UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Usuario ListarLogin(String login) {
        return null;
    }

    @Override
    @Transactional
    public Usuario SalvarUsuario(Usuario usuario) {
        String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senhaCriptografada);
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario DeletarUsuario(Integer id) {
        return null;
    }

    @Override
    public Usuario AlterarUsuario(Integer id, Usuario usuario) {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado na Base de dados"));
        return User
                .builder()
                .username(usuario.getLogin())
                .password(usuario.getSenha())
                .roles("ADMIN")
                .build();
    }

    public UserDetails autenticar(Usuario usuario) {
        UserDetails userDetails = loadUserByUsername(usuario.getLogin());
        boolean senhasBatem = passwordEncoder.matches(usuario.getSenha(), userDetails.getPassword());// 1º deve ser a senha
        if (senhasBatem) {
            return userDetails;
        }
        throw new SenhaInvalidaException();
    }

}
