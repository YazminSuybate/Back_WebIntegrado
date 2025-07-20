package fusion.fusion.controller;


import fusion.fusion.entity.AsesoriaLegal;
import fusion.fusion.entity.SeguimientoAsesoria;
import fusion.fusion.service.AsesoriaLegalService;
import fusion.fusion.service.SeguimientoAsesoriaService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/seguimientos-asesoria")
public class SeguimientoAsesoriaController {

    private final SeguimientoAsesoriaService seguimientoAsesoriaService;
    private final AsesoriaLegalService asesoriaLegalService;


    public SeguimientoAsesoriaController(
            SeguimientoAsesoriaService seguimientoAsesoriaService,
            AsesoriaLegalService asesoriaLegalService) {
        this.seguimientoAsesoriaService = seguimientoAsesoriaService;
        this.asesoriaLegalService = asesoriaLegalService;
    }


    @PostMapping
    public ResponseEntity<?> crearSeguimiento(@RequestBody SeguimientoAsesoriaDTO dto) {
        Optional<AsesoriaLegal> asesoriaOpt = asesoriaLegalService.obtenerAsesoriaPorId(dto.getAsesoriaId());

        if (asesoriaOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "Asesoría no encontrada"));
        }

        AsesoriaLegal asesoriaRef = new AsesoriaLegal();
        asesoriaRef.setId(dto.getAsesoriaId()); // Solo referencia, no trae relaciones

        SeguimientoAsesoria seguimiento = SeguimientoAsesoria.builder()
                .descripcion(dto.getDescripcion())
                .fechaSeguimiento(dto.getFechaSeguimiento())
                .asesoria(asesoriaRef)
                .build();

        seguimientoAsesoriaService.guardarSeguimiento(seguimiento);

        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "Seguimiento guardado correctamente"));
    }





    @PostMapping("/prueba")
    public String testPost() {
        return "Funciona POST";
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