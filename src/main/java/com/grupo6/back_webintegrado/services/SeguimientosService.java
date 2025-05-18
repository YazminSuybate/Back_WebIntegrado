package com.grupo6.back_webintegrado.services;

import com.grupo6.back_webintegrado.model.entity.Seguimientos;

import java.util.List;
import java.util.Optional;

public interface SeguimientosService {

    Seguimientos saveSeguimiento (Seguimientos seguimiento);

    List<Seguimientos> getAllSeguimientos ();

    Optional<Seguimientos> getSeguimientoById(Long id);

    Seguimientos updateSeguimiento (Seguimientos seguimientosUpdate);

    void deleteSeguimiento(long id);

}
