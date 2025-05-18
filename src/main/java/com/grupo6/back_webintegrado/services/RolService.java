package com.grupo6.back_webintegrado.services;

import com.grupo6.back_webintegrado.model.entity.Rol;

import java.util.List;
import java.util.Optional;

public interface RolService {

    Rol saveRol(Rol rol);

    List<Rol> getAllRoles();

    Optional<Rol> getRolById(Long id);

    Rol updateRol(Rol rolUpdate);

    void deleteRol(long id);

}
