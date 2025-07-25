package fusion.fusion.controller;

import fusion.fusion.entity.DiagnosticoPsicologico;
import fusion.fusion.entity.SesionPsicologica;
import fusion.fusion.service.DiagnosticoPsicologicoService;

import fusion.fusion.service.SesionPsicologicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/diagnosticos-psicologicos")
public class DiagnosticoPsicologicoController {

    private final DiagnosticoPsicologicoService diagnosticoPsicologicoService;

@Autowired
    private  SesionPsicologicaService sesionPsicologicaService;

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




    @PostMapping("/crear-diagnosticos")
    public ResponseEntity<?> crearDiagnostico(@RequestBody DiagnosticoPsicologicoDTO dto) {
        Optional<SesionPsicologica> sesionOpt = sesionPsicologicaService.obtenerSesionPorId(dto.getSesionId());

        if (sesionOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "Sesión psicológica no encontrada"));
        }

        // Solo referencia a la sesión
        SesionPsicologica sesionRef = new SesionPsicologica();
        sesionRef.setId(dto.getSesionId());

        DiagnosticoPsicologico diagnostico = DiagnosticoPsicologico.builder()
                .recomendaciones(dto.getRecomendaciones())
                .fechaDiagnostico(dto.getFechaDiagnostico())
                .sesion(sesionRef)
                .build();

        diagnosticoPsicologicoService.guardarDiagnostico(diagnostico);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of("message", "Diagnóstico guardado correctamente"));
}






}