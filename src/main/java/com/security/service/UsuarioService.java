package com.security.service;

import com.security.model.entity.Usuario;

public interface UsuarioService {

    Usuario ListarLogin(String login);

    Usuario SalvarUsuario(Usuario usuario);

    Usuario DeletarUsuario(Integer id);

    Usuario AlterarUsuario(Integer id, Usuario usuario);

}
