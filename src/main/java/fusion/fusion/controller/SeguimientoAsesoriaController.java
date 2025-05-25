package fusion.fusion.controller;


import fusion.fusion.entity.SeguimientoAsesoria;
import fusion.fusion.service.SeguimientoAsesoriaService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seguimientos-asesoria")
public class SeguimientoAsesoriaController {

    private final SeguimientoAsesoriaService seguimientoAsesoriaService;

    public SeguimientoAsesoriaController(SeguimientoAsesoriaService seguimientoAsesoriaService) {
        this.seguimientoAsesoriaService = seguimientoAsesoriaService;
    }

    @PostMapping
    public ResponseEntity<SeguimientoAsesoria> crearSeguimiento(@RequestBody SeguimientoAsesoria seguimientoAsesoria) {
        SeguimientoAsesoria nuevoSeguimiento = seguimientoAsesoriaService.guardarSeguimiento(seguimientoAsesoria);
        return new ResponseEntity<>(nuevoSeguimiento, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<SeguimientoAsesoria>> obtenerTodosLosSeguimientos() {
        List<SeguimientoAsesoria> seguimientos = seguimientoAsesoriaService.obtenerTodosLosSeguimientos();
        return new ResponseEntity<>(seguimientos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SeguimientoAsesoria> obtenerSeguimientoPorId(@PathVariable Long id) {
        return seguimientoAsesoriaService.obtenerSeguimientoPorId(id)
                .map(seguimiento -> new ResponseEntity<>(seguimiento, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SeguimientoAsesoria> actualizarSeguimiento(@PathVariable Long id, @RequestBody SeguimientoAsesoria seguimientoAsesoria) {
        return seguimientoAsesoriaService.obtenerSeguimientoPorId(id)
                .map(seguimientoExistente -> {
                    seguimientoAsesoria.setId(id);
                    SeguimientoAsesoria seguimientoActualizado = seguimientoAsesoriaService.guardarSeguimiento(seguimientoAsesoria);
                    return new ResponseEntity<>(seguimientoActualizado, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarSeguimiento(@PathVariable Long id) {
        return seguimientoAsesoriaService.obtenerSeguimientoPorId(id)
                .map(seguimiento -> {
                    seguimientoAsesoriaService.eliminarSeguimiento(id);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/asesoria/{asesoriaId}")
    public ResponseEntity<List<SeguimientoAsesoria>> obtenerSeguimientosPorAsesoria(@PathVariable Long asesoriaId) {
        List<SeguimientoAsesoria> seguimientos = seguimientoAsesoriaService.obtenerSeguimientosPorAsesoriaId(asesoriaId);
        return new ResponseEntity<>(seguimientos, HttpStatus.OK);
    }
}