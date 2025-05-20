package com.grupo6.back_webintegrado.services.impl;

import com.grupo6.back_webintegrado.exception.ResourceNotFoundException;
import com.grupo6.back_webintegrado.model.entity.Seguimientos;
import com.grupo6.back_webintegrado.repository.SeguimientosRepository;
import com.grupo6.back_webintegrado.services.SeguimientosService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class SeguimientosServiceImpl implements SeguimientosService {

    @Autowired
    SeguimientosRepository seguimientosRepository;

    @Override
    public Seguimientos saveSeguimiento(Seguimientos seguimiento) {
        Optional<Seguimientos> seguimientoSave = seguimientosRepository.findById(seguimiento.getId());
        if (seguimientoSave.isPresent()) {
            throw new ResourceNotFoundException("El seguimiento ya existe:" + seguimiento.getId());
        }
        return seguimientosRepository.save(seguimiento);
    }

    @Override
    public List<Seguimientos> getAllSeguimientos() {
        return seguimientosRepository.findAll();
    }

    @Override
    public Optional<Seguimientos> getSeguimientoById(Long id) {
        return seguimientosRepository.findById(id);
    }

    @Override
    public Seguimientos updateSeguimiento(Seguimientos seguimientosUpdate) {
        return seguimientosRepository.save(seguimientosUpdate);
    }

    @Override
    public void deleteSeguimiento(long id) {
        seguimientosRepository.deleteById(id);
    }
}
