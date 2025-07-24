package fusion.fusion.controller;

import fusion.fusion.entity.SesionPsicologica;
import fusion.fusion.io.SesionPrograRequest;
import fusion.fusion.io.SesionRequest;
import fusion.fusion.service.DenunciaService;
import fusion.fusion.service.SesionPsicologicaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/sesiones-psicologicas")
public class SesionPsicologicaController {

    @Autowired
    private DenunciaService denunciaService;
    private final SesionPsicologicaService sesionPsicologicaService;

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

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<SesionPsicologica>> obtenerSesionesPorEstado(@PathVariable String estado) {
        List<SesionPsicologica> sesiones = sesionPsicologicaService.obtenerSesionesPorEstado(estado);
        return new ResponseEntity<>(sesiones, HttpStatus.OK);
    }

    @PostMapping("/solicitar/{id}")
    public ResponseEntity<SesionPsicologica> solicitarSesion(@PathVariable Long denunciaId,@RequestBody SesionRequest sesionRequest) {

        SesionPsicologica sesionPsicologica = new SesionPsicologica();
        sesionPsicologica.setDescripcion(sesionRequest.getDescripcion());
        sesionPsicologica.setDenuncia(denunciaService.obtenerDenunciaPorId(denunciaId).get());
        sesionPsicologica.setEstado("SOLICITADA");
        sesionPsicologica.setFechaSolicitud(LocalDateTime.now());

        sesionPsicologicaService.guardarSesion(sesionPsicologica);

        return new ResponseEntity<>(sesionPsicologica, HttpStatus.CREATED);
    }


    @PostMapping("programar/{id}")
    public ResponseEntity<SesionPsicologica> programarSesion(@PathVariable Long sesionId,@RequestBody SesionPrograRequest sesionRequest) {

        SesionPsicologica sesionPsicologica = sesionPsicologicaService.obtenerSesionPorId(sesionId).get();
        sesionPsicologica.setFechaSesion(LocalDateTime.from(sesionRequest.getFecha()));
        sesionPsicologica.setDuracion(sesionRequest.getDuracion());
        sesionPsicologica.setEstado("PROGRAMADA");

        return new ResponseEntity<>(sesionPsicologica, HttpStatus.CREATED);
    }

}