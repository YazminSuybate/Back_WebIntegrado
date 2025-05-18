package com.grupo6.back_webintegrado.services.impl;

import com.grupo6.back_webintegrado.exception.ResourceNotFoundException;
import com.grupo6.back_webintegrado.model.entity.Archivos;
import com.grupo6.back_webintegrado.repository.ArchivosRepository;
import com.grupo6.back_webintegrado.services.ArchivosService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class ArchivosServiceImpl implements ArchivosService {

    @Autowired
    ArchivosRepository archivosRepository;

    @Override
    public Archivos saveArchivo(Archivos archivo) {
        Optional<Archivos> archivosSave = archivosRepository.findById(archivo.getId());
        if (archivosSave.isPresent()) {
            throw new ResourceNotFoundException("El archivo ya existe:" + archivo.getId());
        }
        return archivosRepository.save(archivo);
    }

    @Override
    public List<Archivos> getAllArchivos() {
        return archivosRepository.findAll();
    }

    @Override
    public Optional<Archivos> getArchivoById(Long id) {
        return archivosRepository.findById(id);
    }

    @Override
    public Archivos updateArchivo(Archivos archivoUpdate) {
        return archivosRepository.save(archivoUpdate);
    }

    @Override
    public void deleteArchivo(long id) {
        archivosRepository.deleteById(id);
    }
}
