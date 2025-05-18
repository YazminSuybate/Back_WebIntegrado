package com.grupo6.back_webintegrado.services;

import com.grupo6.back_webintegrado.model.entity.SesionesPsico;

import java.util.List;
import java.util.Optional;

public interface SesionesPsicoServices {

    SesionesPsico saveSesionPsico(SesionesPsico sesionPsico);

    List<SesionesPsico> getAllSesionesPsico();

    Optional<SesionesPsico> getSesionPsicoById(Long id);

    SesionesPsico updateSesionesPsico(SesionesPsico sesionesPsicoUpdate);

    void deleteSesionPsico(long id);

}
