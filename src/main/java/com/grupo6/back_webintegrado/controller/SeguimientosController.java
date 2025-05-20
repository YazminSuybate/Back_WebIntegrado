package com.grupo6.back_webintegrado.controller;

import com.grupo6.back_webintegrado.model.entity.Seguimientos;
import com.grupo6.back_webintegrado.services.SeguimientosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seguimientos")
public class SeguimientosController {

    @Autowired
    SeguimientosService seguimientosService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Seguimientos saveSeguimiento(@RequestBody Seguimientos seguimiento) {
        return seguimientosService.saveSeguimiento(seguimiento);
    }

    @GetMapping
    public List<Seguimientos> findSeguimientos() {
        return seguimientosService.getAllSeguimientos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Seguimientos> getSeguimientoById(@PathVariable long id) {
        return seguimientosService.getSeguimientoById(id)
                .map(ResponseEntity :: ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Seguimientos> updateSeguimiento(@PathVariable long id, @RequestBody Seguimientos seguimiento) {
        return seguimientosService.getSeguimientoById(id)
                .map(seguimientoSave -> {
                    seguimientoSave.setFecha(seguimiento.getFecha());
                    seguimientoSave.setDenuncia(seguimiento.getDenuncia());
                    seguimientoSave.setComentario(seguimiento.getComentario());

                    Seguimientos seguimientoUpdate = seguimientosService.updateSeguimiento(seguimientoSave);
                    return new ResponseEntity<>(seguimientoUpdate, HttpStatus.OK);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSeguimiento(@PathVariable long id) {
        seguimientosService.deleteSeguimiento(id);
        return new ResponseEntity<>("Seguimiento eliminado", HttpStatus.OK);
    }

}
