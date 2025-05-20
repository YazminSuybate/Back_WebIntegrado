package com.grupo6.back_webintegrado.services.impl;

import com.grupo6.back_webintegrado.exception.ResourceNotFoundException;
import com.grupo6.back_webintegrado.model.entity.DiagnosticosPsico;
import com.grupo6.back_webintegrado.repository.DiagnosticosPsicoRepository;
import com.grupo6.back_webintegrado.services.DiagnosticosPsicoService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class DiagnosticosPsicoServiceImpl implements DiagnosticosPsicoService {

    @Autowired
    DiagnosticosPsicoRepository diagnosticosPsicoRepository;

    @Override
    public DiagnosticosPsico saveDiagnosticoPsico(DiagnosticosPsico diagnosticosPsico) {
        Optional<DiagnosticosPsico> diagnosticosPsicoSave = diagnosticosPsicoRepository.findById(diagnosticosPsico.getId());
        if (diagnosticosPsicoSave.isPresent()) {
            throw new ResourceNotFoundException("El diagnostico psicologico ya existe:" + diagnosticosPsico.getId());
        }
        return diagnosticosPsicoRepository.save(diagnosticosPsico);
    }

    @Override
    public List<DiagnosticosPsico> getAllDiagnosticosPsico() {
        return diagnosticosPsicoRepository.findAll();
    }

    @Override
    public Optional<DiagnosticosPsico> getDiagnosticoPsicoById(Long id) {
        return diagnosticosPsicoRepository.findById(id);
    }

    @Override
    public DiagnosticosPsico updateDiagnosticoPsico(DiagnosticosPsico diagnosticosPsicoUpdate) {

        return diagnosticosPsicoRepository.save(diagnosticosPsicoUpdate);
    }

    @Override
    public void deleteDiagnosticoPsico(long id) {
        diagnosticosPsicoRepository.deleteById(id);
    }
}
