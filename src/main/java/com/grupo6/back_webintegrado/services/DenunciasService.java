package com.grupo6.back_webintegrado.services;

import com.grupo6.back_webintegrado.model.entity.Denuncias;

import java.util.List;
import java.util.Optional;

public interface DenunciasService {

    Denuncias saveDenuncia(Denuncias denuncia);

    List<Denuncias> getAllDenuncias();

    Optional<Denuncias> getDenunciaById(Long id);

    Denuncias updateDenuncia(Denuncias denunciaUpdate);

    void deleteDenuncia(long id);

}
