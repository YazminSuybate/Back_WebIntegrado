package com.grupo6.back_webintegrado.services;

import com.grupo6.back_webintegrado.model.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Usuario saveUser(Usuario usuario);

    List<Usuario> getAllUsers();

    Optional<Usuario> getUsuarioById(Long id);

    Usuario updateEmpleado(Usuario userUpdate);

    void deleteUser(long id);
}
