package com.grupo6.back_webintegrado.controller;

import com.grupo6.back_webintegrado.model.entity.DiagnosticosPsico;
import com.grupo6.back_webintegrado.services.DiagnosticosPsicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/diagnosticos")
public class DiagnosticosPsicoController {

    @Autowired
    private DiagnosticosPsicoService diagnosticosPsicoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DiagnosticosPsico saveDiagnostico(@RequestBody DiagnosticosPsico diagnosticoPsico) {
        return diagnosticosPsicoService.saveDiagnosticoPsico(diagnosticoPsico);
    }

    @GetMapping
    public List<DiagnosticosPsico> findDiagnosticos() {
        return diagnosticosPsicoService.getAllDiagnosticosPsico();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiagnosticosPsico> getDiagnosticoById(@PathVariable long id) {
        return diagnosticosPsicoService.getDiagnosticoPsicoById(id)
                .map(ResponseEntity :: ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public  ResponseEntity<DiagnosticosPsico> updateDiagnostico(@PathVariable long id, @RequestBody DiagnosticosPsico diagnosticoPsico) {
        return diagnosticosPsicoService.getDiagnosticoPsicoById(id)
                .map(diagnosticoPsicoSave -> {
                    diagnosticoPsicoSave.setDescripcion(diagnosticoPsico.getDescripcion());
                    diagnosticoPsicoSave.setRecomendaciones(diagnosticoPsico.getRecomendaciones());
                    diagnosticoPsicoSave.setSesion(diagnosticoPsico.getSesion());

                    DiagnosticosPsico diagnosticoPsicoUpdate = diagnosticosPsicoService.updateDiagnosticoPsico(diagnosticoPsicoSave);
                    return new ResponseEntity<>(diagnosticoPsicoUpdate, HttpStatus.OK);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDiagnostico(@PathVariable long id) {
        diagnosticosPsicoService.deleteDiagnosticoPsico(id);
        return new ResponseEntity<>("Diagnostico eliminado", HttpStatus.OK);
    }

}
