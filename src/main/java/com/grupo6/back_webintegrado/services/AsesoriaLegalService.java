package com.grupo6.back_webintegrado.services;

import com.grupo6.back_webintegrado.model.entity.AsesoriaLegal;

import java.util.List;
import java.util.Optional;

public interface AsesoriaLegalService {

    AsesoriaLegal saveAsesoriaLegal(AsesoriaLegal asesoriaLegal);

    List<AsesoriaLegal> getAllAsesoriaLegal();

    Optional<AsesoriaLegal> getAsesoriaLegalById(Long id);

    AsesoriaLegal updateAsesoriaLegal(AsesoriaLegal asesoriaLegalUpdate);

    void deleteAsesoriaLegal(long id);

}
