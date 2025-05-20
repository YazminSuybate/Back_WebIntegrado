package com.grupo6.back_webintegrado.services.impl;

import com.grupo6.back_webintegrado.exception.ResourceNotFoundException;
import com.grupo6.back_webintegrado.model.entity.SesionesPsico;
import com.grupo6.back_webintegrado.repository.SesionesPsicoRepository;
import com.grupo6.back_webintegrado.services.SesionesPsicoServices;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class SesionesPsicoServiceImpl implements SesionesPsicoServices {

    @Autowired
    SesionesPsicoRepository sesionesPsicoRepository;

    @Override
    public SesionesPsico saveSesionPsico(SesionesPsico sesionPsico) {
        Optional<SesionesPsico> sesionPsicoSave = sesionesPsicoRepository.findById(sesionPsico.getId());
        if (sesionPsicoSave.isPresent()) {
            throw new ResourceNotFoundException("La sesion psicologica ya existe:" + sesionPsico.getId());
        }
        return sesionesPsicoRepository.save(sesionPsico);
    }

    @Override
    public List<SesionesPsico> getAllSesionesPsico() {
        return sesionesPsicoRepository.findAll();
    }

    @Override
    public Optional<SesionesPsico> getSesionPsicoById(Long id) {
        return sesionesPsicoRepository.findById(id);
    }

    @Override
    public SesionesPsico updateSesionesPsico(SesionesPsico sesionPsicoUpdate) {
        return sesionesPsicoRepository.save(sesionPsicoUpdate);
    }

    @Override
    public void deleteSesionPsico(long id) {
        sesionesPsicoRepository.deleteById(id);
    }
}
