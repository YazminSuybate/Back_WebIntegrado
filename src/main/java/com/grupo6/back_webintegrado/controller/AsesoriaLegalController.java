package com.grupo6.back_webintegrado.controller;

import com.grupo6.back_webintegrado.model.entity.AsesoriaLegal;
import com.grupo6.back_webintegrado.services.AsesoriaLegalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/asesorias")
public class AsesoriaLegalController {

    @Autowired
    private AsesoriaLegalService asesoriaLegalService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AsesoriaLegal saveAsesoriaLegal(@RequestBody AsesoriaLegal asesoriaLegal) {
        return asesoriaLegalService.saveAsesoriaLegal(asesoriaLegal);
    }

    @GetMapping
    public List<AsesoriaLegal> findAsesorias() {
        return asesoriaLegalService.getAllAsesoriaLegal();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AsesoriaLegal> getAsesoriaById(@PathVariable long id) {
        return asesoriaLegalService.getAsesoriaLegalById(id)
                .map(ResponseEntity :: ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<AsesoriaLegal> updateAsesoria(@PathVariable long id, @RequestBody AsesoriaLegal asesoriaLegal) {
        return asesoriaLegalService.getAsesoriaLegalById(id)
                .map(asesoriaLegalSave -> {
                    asesoriaLegalSave.setEmpleado(asesoriaLegal.getEmpleado());
                    asesoriaLegalSave.setUsuario(asesoriaLegal.getUsuario());
                    asesoriaLegalSave.setFecha(asesoriaLegal.getFecha());
                    asesoriaLegalSave.setTema(asesoriaLegal.getTema());
                    asesoriaLegalSave.setResumen(asesoriaLegal.getResumen());

                    AsesoriaLegal asesoriaLegalUpdate = asesoriaLegalService.updateAsesoriaLegal(asesoriaLegalSave);
                    return new ResponseEntity<>(asesoriaLegalUpdate, HttpStatus.OK);

                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAsesoria(@PathVariable long id) {
        asesoriaLegalService.deleteAsesoriaLegal(id);
        return new ResponseEntity<>("Asesoría eliminada", HttpStatus.OK);
    }

}
