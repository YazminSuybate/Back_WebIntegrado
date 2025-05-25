package fusion.fusion.controller.controladorRestante;

import fusion.fusion.entity.entidadesRestantes.SesionPsicologica;
import fusion.fusion.service.serviceRestante.SesionPsicologicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sesiones-psicologicas")
public class SesionPsicologicaController {

    private final SesionPsicologicaService sesionPsicologicaService;

    @Autowired
    public SesionPsicologicaController(SesionPsicologicaService sesionPsicologicaService) {
        this.sesionPsicologicaService = sesionPsicologicaService;
    }

    @PostMapping
    public ResponseEntity<SesionPsicologica> crearSesion(@RequestBody SesionPsicologica sesionPsicologica) {
        SesionPsicologica nuevaSesion = sesionPsicologicaService.guardarSesion(sesionPsicologica);
        return new ResponseEntity<>(nuevaSesion, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<SesionPsicologica>> obtenerTodasLasSesiones() {
        List<SesionPsicologica> sesiones = sesionPsicologicaService.obtenerTodasLasSesiones();
        return new ResponseEntity<>(sesiones, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SesionPsicologica> obtenerSesionPorId(@PathVariable Long id) {
        return sesionPsicologicaService.obtenerSesionPorId(id)
                .map(sesion -> new ResponseEntity<>(sesion, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SesionPsicologica> actualizarSesion(@PathVariable Long id, @RequestBody SesionPsicologica sesionPsicologica) {
        return sesionPsicologicaService.obtenerSesionPorId(id)
                .map(sesionExistente -> {
                    sesionPsicologica.setId(id);
                    SesionPsicologica sesionActualizada = sesionPsicologicaService.guardarSesion(sesionPsicologica);
                    return new ResponseEntity<>(sesionActualizada, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarSesion(@PathVariable Long id) {
        return sesionPsicologicaService.obtenerSesionPorId(id)
                .map(sesion -> {
                    sesionPsicologicaService.eliminarSesion(id);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<SesionPsicologica>> obtenerSesionesPorPaciente(@PathVariable Long pacienteId) {
        List<SesionPsicologica> sesiones = sesionPsicologicaService.obtenerSesionesPorPacienteId(pacienteId);
        return new ResponseEntity<>(sesiones, HttpStatus.OK);
    }

    @GetMapping("/psicologo/{psicologoId}")
    public ResponseEntity<List<SesionPsicologica>> obtenerSesionesPorPsicologo(@PathVariable Long psicologoId) {
        List<SesionPsicologica> sesiones = sesionPsicologicaService.obtenerSesionesPorPsicologoId(psicologoId);
        return new ResponseEntity<>(sesiones, HttpStatus.OK);
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<SesionPsicologica>> obtenerSesionesPorEstado(@PathVariable String estado) {
        List<SesionPsicologica> sesiones = sesionPsicologicaService.obtenerSesionesPorEstado(estado);
        return new ResponseEntity<>(sesiones, HttpStatus.OK);
    }
}