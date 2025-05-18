package com.grupo6.back_webintegrado.services;

import com.grupo6.back_webintegrado.model.entity.DiagnosticosPsico;

import java.util.List;
import java.util.Optional;

public interface DiagnosticosPsicoService {

    DiagnosticosPsico saveDiagnosticoPsico (DiagnosticosPsico diagnosticosPsico);

    List<DiagnosticosPsico> getAllDiagnosticosPsico();

    Optional<DiagnosticosPsico> getDiagnosticoPsicoById(Long id);

    DiagnosticosPsico updateDiagnosticoPsico(DiagnosticosPsico diagnosticosPsicoUpdate);

    void deleteDiagnosticoPsico(long id);

}
