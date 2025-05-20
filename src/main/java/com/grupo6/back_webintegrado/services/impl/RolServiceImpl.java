package com.grupo6.back_webintegrado.services.impl;

import com.grupo6.back_webintegrado.exception.ResourceNotFoundException;
import com.grupo6.back_webintegrado.model.entity.Rol;
import com.grupo6.back_webintegrado.repository.RolRepository;
import com.grupo6.back_webintegrado.services.RolService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class RolServiceImpl implements RolService {

    @Autowired
    RolRepository rolRepository;

    @Override
    public Rol saveRol(Rol rol) {
        Optional<Rol> rolSave = rolRepository.findById(rol.getId());
        if (rolSave.isPresent()) {
            throw new ResourceNotFoundException("El rol ya existe:" + rol.getId());
        }
        return rolRepository.save(rol);
    }

    @Override
    public List<Rol> getAllRoles() {
        return rolRepository.findAll();
    }

    @Override
    public Optional<Rol> getRolById(Long id) {
        return rolRepository.findById(id);
    }

    @Override
    public Rol updateRol(Rol rolUpdate) {
        return rolRepository.save(rolUpdate);
    }

    @Override
    public void deleteRol(long id) {
        rolRepository.deleteById(id);
    }
}
