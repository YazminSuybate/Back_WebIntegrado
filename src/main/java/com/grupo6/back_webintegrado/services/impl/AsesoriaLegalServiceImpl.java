package com.grupo6.back_webintegrado.services.impl;

import com.grupo6.back_webintegrado.exception.ResourceNotFoundException;
import com.grupo6.back_webintegrado.model.entity.AsesoriaLegal;
import com.grupo6.back_webintegrado.repository.AsesoriaLegalRepository;
import com.grupo6.back_webintegrado.services.AsesoriaLegalService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class AsesoriaLegalServiceImpl implements AsesoriaLegalService {

    @Autowired
    AsesoriaLegalRepository asesoriaLegalRepository;

    @Override
    public AsesoriaLegal saveAsesoriaLegal(AsesoriaLegal asesoriaLegal) {
        Optional<AsesoriaLegal> asesoriaLegalSave = asesoriaLegalRepository.findById(asesoriaLegal.getId());
        if (asesoriaLegalSave.isPresent()) {
            throw new ResourceNotFoundException("La asesoria legal ya existe:" + asesoriaLegal.getId());
        }
        return asesoriaLegalRepository.save(asesoriaLegal);
    }

    @Override
    public List<AsesoriaLegal> getAllAsesoriaLegal() {
        return asesoriaLegalRepository.findAll();
    }

    @Override
    public Optional<AsesoriaLegal> getAsesoriaLegalById(Long id) {
        return asesoriaLegalRepository.findById(id);
    }

    @Override
    public AsesoriaLegal updateAsesoriaLegal(AsesoriaLegal asesoriaLegalUpdate) {
        return asesoriaLegalRepository.save(asesoriaLegalUpdate)    ;
    }

    @Override
    public void deleteAsesoriaLegal(long id) {
        asesoriaLegalRepository.deleteById(id);
    }
}
