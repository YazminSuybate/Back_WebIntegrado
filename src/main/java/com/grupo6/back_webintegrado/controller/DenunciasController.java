package com.grupo6.back_webintegrado.controller;

import com.grupo6.back_webintegrado.model.entity.Denuncias;
import com.grupo6.back_webintegrado.services.DenunciasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/denuncias")
public class DenunciasController {

    @Autowired
    private DenunciasService denunciasService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Denuncias saveDenuncia(@RequestBody Denuncias denuncia) {
        return denunciasService.saveDenuncia(denuncia);
    }

    @GetMapping
    public List<Denuncias> obtenerDenuncias() {
       return denunciasService.getAllDenuncias();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Denuncias> getDenunciaById(@PathVariable long id) {
        return denunciasService.getDenunciaById(id)
                .map(ResponseEntity :: ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("{id}")
    public ResponseEntity<Denuncias> updateDenuncia(@PathVariable long id, @RequestBody Denuncias denuncia) {
        return denunciasService.getDenunciaById(id)
                .map( denunciaSave -> {
                    denunciaSave.setArchivo(denuncia.getArchivo());
                    denunciaSave.setEstado(denuncia.getEstado());
                    denunciaSave.setDescripcion(denuncia.getDescripcion());
                    denunciaSave.setFecha(denuncia.getFecha());
                    denunciaSave.setSeguimiento(denuncia.getSeguimiento());
                    denunciaSave.setUsuario(denuncia.getUsuario());

                    Denuncias denunciaUpdate = denunciasService.saveDenuncia(denunciaSave);
                    return new ResponseEntity<>(denunciaUpdate, HttpStatus.OK);

                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDenuncia(@PathVariable long id) {
        denunciasService.deleteDenuncia(id);
        return new ResponseEntity<>("Denuncia eliminada", HttpStatus.OK);
    }
}
