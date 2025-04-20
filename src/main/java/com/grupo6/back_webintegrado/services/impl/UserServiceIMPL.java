package com.grupo6.back_webintegrado.services.impl;

import com.grupo6.back_webintegrado.exception.ResourceNotFoundException;
import com.grupo6.back_webintegrado.model.entity.Usuario;
import com.grupo6.back_webintegrado.repository.UserRepository;
import com.grupo6.back_webintegrado.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceIMPL implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Usuario saveUser(Usuario usuario) {
        Optional<Usuario> userSave = userRepository.findById(usuario.getId());
        if(userSave.isPresent()){
            throw new ResourceNotFoundException("El usuario ya existe: " + usuario.getId());
        }
        return userRepository.save(usuario);
    }

    @Override
    public List<Usuario> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<Usuario> getUsuarioById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Usuario updateEmpleado(Usuario userUpdate) {
        return userRepository.save(userUpdate);
    }

    @Override
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }
}
