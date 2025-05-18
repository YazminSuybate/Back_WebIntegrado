package com.grupo6.back_webintegrado.services.impl;

import com.grupo6.back_webintegrado.exception.ResourceNotFoundException;
import com.grupo6.back_webintegrado.model.entity.Denuncias;
import com.grupo6.back_webintegrado.repository.DenunciasRepository;
import com.grupo6.back_webintegrado.services.DenunciasService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class DenunciasServiceImpl implements DenunciasService {

    @Autowired
    DenunciasRepository denunciasRepository;

    @Override
    public Denuncias saveDenuncia(Denuncias denuncia) {
        Optional<Denuncias> denunciaSave = denunciasRepository.findById(denuncia.getId());
        if(denunciaSave.isPresent()){
            throw new ResourceNotFoundException("La denuncia ya existe: " + denuncia.getId());
        }
        return denunciasRepository.save(denuncia);
    }

    @Override
    public List<Denuncias> getAllDenuncias() { return denunciasRepository.findAll(); }

    @Override
    public Optional<Denuncias> getDenunciaById(Long id) {
        return denunciasRepository.findById(id);
    }

    @Override
    public Denuncias updateDenuncia(Denuncias denunciaUpdate) {
        return denunciasRepository.save(denunciaUpdate);
    }

    @Override
    public void deleteDenuncia(long id) {
        denunciasRepository.deleteById(id);
    }
}
