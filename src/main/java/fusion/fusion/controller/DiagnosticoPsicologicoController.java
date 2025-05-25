package fusion.fusion.controller;

import fusion.fusion.entity.DiagnosticoPsicologico;
import fusion.fusion.service.DiagnosticoPsicologicoService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/diagnosticos-psicologicos")
public class DiagnosticoPsicologicoController {

    private final DiagnosticoPsicologicoService diagnosticoPsicologicoService;

    public DiagnosticoPsicologicoController(DiagnosticoPsicologicoService diagnosticoPsicologicoService) {
        this.diagnosticoPsicologicoService = diagnosticoPsicologicoService;
    }

    @PostMapping
    public ResponseEntity<DiagnosticoPsicologico> crearDiagnostico(@RequestBody DiagnosticoPsicologico diagnosticoPsicologico) {
        DiagnosticoPsicologico nuevoDiagnostico = diagnosticoPsicologicoService.guardarDiagnostico(diagnosticoPsicologico);
        return new ResponseEntity<>(nuevoDiagnostico, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DiagnosticoPsicologico>> obtenerTodosLosDiagnosticos() {
        List<DiagnosticoPsicologico> diagnosticos = diagnosticoPsicologicoService.obtenerTodosLosDiagnosticos();
        return new ResponseEntity<>(diagnosticos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiagnosticoPsicologico> obtenerDiagnosticoPorId(@PathVariable Long id) {
        return diagnosticoPsicologicoService.obtenerDiagnosticoPorId(id)
                .map(diagnostico -> new ResponseEntity<>(diagnostico, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DiagnosticoPsicologico> actualizarDiagnostico(@PathVariable Long id, @RequestBody DiagnosticoPsicologico diagnosticoPsicologico) {
        return diagnosticoPsicologicoService.obtenerDiagnosticoPorId(id)
                .map(diagnosticoExistente -> {
                    diagnosticoPsicologico.setId(id);
                    DiagnosticoPsicologico diagnosticoActualizado = diagnosticoPsicologicoService.guardarDiagnostico(diagnosticoPsicologico);
                    return new ResponseEntity<>(diagnosticoActualizado, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarDiagnostico(@PathVariable Long id) {
        return diagnosticoPsicologicoService.obtenerDiagnosticoPorId(id)
                .map(diagnostico -> {
                    diagnosticoPsicologicoService.eliminarDiagnostico(id);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/sesion/{sesionId}")
    public ResponseEntity<List<DiagnosticoPsicologico>> obtenerDiagnosticosPorSesion(@PathVariable Long sesionId) {
        List<DiagnosticoPsicologico> diagnosticos = diagnosticoPsicologicoService.obtenerDiagnosticosPorSesionId(sesionId);
        return new ResponseEntity<>(diagnosticos, HttpStatus.OK);
    }
}