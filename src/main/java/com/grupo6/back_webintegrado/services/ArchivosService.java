package com.grupo6.back_webintegrado.services;

import com.grupo6.back_webintegrado.model.entity.Archivos;

import java.util.List;
import java.util.Optional;

public interface ArchivosService {

    Archivos saveArchivo(Archivos archivo);

    List<Archivos> getAllArchivos();

    Optional<Archivos> getArchivoById(Long id);

    Archivos updateArchivo(Archivos archivoUpdate);

    void deleteArchivo(long id);

}
