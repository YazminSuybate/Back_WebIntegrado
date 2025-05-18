package com.grupo6.back_webintegrado.services.impl;

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
        return null;
    }

    @Override
    public List<DiagnosticosPsico> getAllDiagnosticosPsico() {
        return List.of();
    }

    @Override
    public Optional<DiagnosticosPsico> getDiagnosticoPsicoById(Long id) {
        return Optional.empty();
    }

    @Override
    public DiagnosticosPsico updateDiagnosticoPsico(DiagnosticosPsico diagnosticosPsicoUpdate) {
        return null;
    }

    @Override
    public void deleteDiagnosticoPsico(long id) {

    }
}
