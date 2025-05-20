package com.grupo6.back_webintegrado.controller;

import com.grupo6.back_webintegrado.model.entity.Archivos;
import com.grupo6.back_webintegrado.services.ArchivosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/archivos")
public class ArchivosController {

    @Autowired
    private ArchivosService archivosService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Archivos saveArchivo (@RequestBody Archivos archivo) {
        return archivosService.saveArchivo(archivo);
    }

    @GetMapping
    public List<Archivos> findArchivos() {
        return archivosService.getAllArchivos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Archivos> getArchivoById(@PathVariable long id) {
        return archivosService.getArchivoById(id)
                .map(ResponseEntity :: ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Archivos> updateArchivo(@PathVariable long id, @RequestBody Archivos archivo) {
        return archivosService.getArchivoById(id)
                .map(archivoSave -> {
                    archivoSave.setDenuncia(archivo.getDenuncia());
                    archivoSave.setFecha(archivo.getFecha());
                    archivoSave.setTipo(archivo.getTipo());
                    archivoSave.setRuta(archivo.getRuta());
                    archivoSave.setNombre_archi(archivo.getNombre_archi());

                    Archivos archivoUpdate = archivosService.updateArchivo(archivoSave);
                    return new ResponseEntity<>(archivoUpdate, HttpStatus.OK);

                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteArchivo(@PathVariable long id) {
        archivosService.deleteArchivo(id);
        return new ResponseEntity<>("Archivo eliminado", HttpStatus.OK);
    }

}
