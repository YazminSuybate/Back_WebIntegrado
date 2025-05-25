package fusion.fusion.controller.controladorRestante;

import fusion.fusion.entity.entidadesRestantes.SeguimientoDenuncia;
import fusion.fusion.service.serviceRestante.SeguimientoDenunciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seguimientos-denuncia")
public class SeguimientoDenunciaController {

    private final SeguimientoDenunciaService seguimientoDenunciaService;

    @Autowired
    public SeguimientoDenunciaController(SeguimientoDenunciaService seguimientoDenunciaService) {
        this.seguimientoDenunciaService = seguimientoDenunciaService;
    }

    @PostMapping
    public ResponseEntity<SeguimientoDenuncia> crearSeguimiento(@RequestBody SeguimientoDenuncia seguimientoDenuncia) {
        SeguimientoDenuncia nuevoSeguimiento = seguimientoDenunciaService.guardarSeguimiento(seguimientoDenuncia);
        return new ResponseEntity<>(nuevoSeguimiento, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<SeguimientoDenuncia>> obtenerTodosLosSeguimientos() {
        List<SeguimientoDenuncia> seguimientos = seguimientoDenunciaService.obtenerTodosLosSeguimientos();
        return new ResponseEntity<>(seguimientos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SeguimientoDenuncia> obtenerSeguimientoPorId(@PathVariable Long id) {
        return seguimientoDenunciaService.obtenerSeguimientoPorId(id)
                .map(seguimiento -> new ResponseEntity<>(seguimiento, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SeguimientoDenuncia> actualizarSeguimiento(@PathVariable Long id, @RequestBody SeguimientoDenuncia seguimientoDenuncia) {
        return seguimientoDenunciaService.obtenerSeguimientoPorId(id)
                .map(seguimientoExistente -> {
                    seguimientoDenuncia.setId(id);
                    SeguimientoDenuncia seguimientoActualizado = seguimientoDenunciaService.guardarSeguimiento(seguimientoDenuncia);
                    return new ResponseEntity<>(seguimientoActualizado, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarSeguimiento(@PathVariable Long id) {
        return seguimientoDenunciaService.obtenerSeguimientoPorId(id)
                .map(seguimiento -> {
                    seguimientoDenunciaService.eliminarSeguimiento(id);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/denuncia/{denunciaId}")
    public ResponseEntity<List<SeguimientoDenuncia>> obtenerSeguimientosPorDenuncia(@PathVariable Long denunciaId) {
        List<SeguimientoDenuncia> seguimientos = seguimientoDenunciaService.obtenerSeguimientosPorDenunciaId(denunciaId);
        return new ResponseEntity<>(seguimientos, HttpStatus.OK);
    }
}