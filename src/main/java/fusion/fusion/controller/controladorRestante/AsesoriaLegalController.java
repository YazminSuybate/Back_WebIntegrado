package fusion.fusion.controller.controladorRestante;


import fusion.fusion.entity.entidadesRestantes.AsesoriaLegal;
import fusion.fusion.service.serviceRestante.AsesoriaLegalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/asesorias-legales")
public class AsesoriaLegalController {

    private final AsesoriaLegalService asesoriaLegalService;

    @Autowired
    public AsesoriaLegalController(AsesoriaLegalService asesoriaLegalService) {
        this.asesoriaLegalService = asesoriaLegalService;
    }

    @PostMapping
    public ResponseEntity<AsesoriaLegal> crearAsesoria(@RequestBody AsesoriaLegal asesoriaLegal) {
        AsesoriaLegal nuevaAsesoria = asesoriaLegalService.guardarAsesoria(asesoriaLegal);
        return new ResponseEntity<>(nuevaAsesoria, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AsesoriaLegal>> obtenerTodasLasAsesorias() {
        List<AsesoriaLegal> asesorias = asesoriaLegalService.obtenerTodasLasAsesorias();
        return new ResponseEntity<>(asesorias, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AsesoriaLegal> obtenerAsesoriaPorId(@PathVariable Long id) {
        return asesoriaLegalService.obtenerAsesoriaPorId(id)
                .map(asesoria -> new ResponseEntity<>(asesoria, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AsesoriaLegal> actualizarAsesoria(@PathVariable Long id, @RequestBody AsesoriaLegal asesoriaLegal) {
        return asesoriaLegalService.obtenerAsesoriaPorId(id)
                .map(asesoriaExistente -> {
                    asesoriaLegal.setId(id);
                    AsesoriaLegal asesoriaActualizada = asesoriaLegalService.guardarAsesoria(asesoriaLegal);
                    return new ResponseEntity<>(asesoriaActualizada, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarAsesoria(@PathVariable Long id) {
        return asesoriaLegalService.obtenerAsesoriaPorId(id)
                .map(asesoria -> {
                    asesoriaLegalService.eliminarAsesoria(id);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<AsesoriaLegal>> obtenerAsesoriasPorCliente(@PathVariable Long clienteId) {
        List<AsesoriaLegal> asesorias = asesoriaLegalService.obtenerAsesoriasPorClienteId(clienteId);
        return new ResponseEntity<>(asesorias, HttpStatus.OK);
    }

    @GetMapping("/abogado/{abogadoId}")
    public ResponseEntity<List<AsesoriaLegal>> obtenerAsesoriasPorAbogado(@PathVariable Long abogadoId) {
        List<AsesoriaLegal> asesorias = asesoriaLegalService.obtenerAsesoriasPorAbogadoId(abogadoId);
        return new ResponseEntity<>(asesorias, HttpStatus.OK);
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<AsesoriaLegal>> obtenerAsesoriasPorEstado(@PathVariable String estado) {
        List<AsesoriaLegal> asesorias = asesoriaLegalService.obtenerAsesoriasPorEstado(estado);
        return new ResponseEntity<>(asesorias, HttpStatus.OK);
    }
}