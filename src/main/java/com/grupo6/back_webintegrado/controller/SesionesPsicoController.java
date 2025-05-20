package com.grupo6.back_webintegrado.controller;

import com.grupo6.back_webintegrado.model.entity.SesionesPsico;
import com.grupo6.back_webintegrado.services.SesionesPsicoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sesiones")
public class SesionesPsicoController {

    @Autowired
    SesionesPsicoServices sesionesPsicoServices;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SesionesPsico saveSesionPsico(@RequestBody SesionesPsico sesionPsico) {
        return sesionesPsicoServices.saveSesionPsico(sesionPsico);
    }

    @GetMapping
    public List<SesionesPsico> findSesiones() {
        return sesionesPsicoServices.getAllSesionesPsico();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SesionesPsico> getSesionById(@PathVariable long id) {
        return sesionesPsicoServices.getSesionPsicoById(id)
                .map(ResponseEntity :: ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<SesionesPsico> updateSesion(@PathVariable long id, @RequestBody SesionesPsico sesionPsico) {
        return sesionesPsicoServices.getSesionPsicoById(id)
                .map(sesionPsicoSave -> {
                    sesionPsicoSave.setDuracion(sesionPsico.getDuracion());
                    sesionPsicoSave.setUsuario(sesionPsico.getUsuario());
                    sesionPsicoSave.setEmpleado(sesionPsico.getEmpleado());
                    sesionPsicoSave.setFecha(sesionPsico.getFecha());
                    sesionPsicoSave.setObservaciones(sesionPsico.getObservaciones());

                    SesionesPsico sesionesPsicoUpdate = sesionesPsicoServices.updateSesionesPsico(sesionPsicoSave);
                    return new ResponseEntity<>(sesionesPsicoUpdate, HttpStatus.OK);
                })
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSesion(@PathVariable long id) {
        sesionesPsicoServices.deleteSesionPsico(id);
        return new ResponseEntity<>("Sesion eliminada", HttpStatus.OK);
    }

}
