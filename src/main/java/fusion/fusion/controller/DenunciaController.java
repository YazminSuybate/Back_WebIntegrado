package fusion.fusion.controller;


import fusion.fusion.entity.Denuncia;
import fusion.fusion.service.DenunciaService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/denuncias")
public class DenunciaController {

    private final DenunciaService denunciaService;

    public DenunciaController(DenunciaService denunciaService) {
        this.denunciaService = denunciaService;
    }

    @PostMapping
    public ResponseEntity<Denuncia> crearDenuncia(@RequestBody Denuncia denuncia) {
        Denuncia nuevaDenuncia = denunciaService.guardarDenuncia(denuncia);
        return new ResponseEntity<>(nuevaDenuncia, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Denuncia>> obtenerTodasLasDenuncias() {
        List<Denuncia> denuncias = denunciaService.obtenerTodasLasDenuncias();
        return new ResponseEntity<>(denuncias, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Denuncia> obtenerDenunciaPorId(@PathVariable Long id) {
        return denunciaService.obtenerDenunciaPorId(id)
                .map(denuncia -> new ResponseEntity<>(denuncia, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Denuncia> actualizarDenuncia(@PathVariable Long id, @RequestBody Denuncia denuncia) {
        return denunciaService.obtenerDenunciaPorId(id)
                .map(denunciaExistente -> {
                    denuncia.setId(id);
                    Denuncia denunciaActualizada = denunciaService.guardarDenuncia(denuncia);
                    return new ResponseEntity<>(denunciaActualizada, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarDenuncia(@PathVariable Long id) {
        return denunciaService.obtenerDenunciaPorId(id)
                .map(denuncia -> {
                    denunciaService.eliminarDenuncia(id);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Denuncia>> obtenerDenunciasPorEstado(@PathVariable String estado) {
        List<Denuncia> denuncias = denunciaService.obtenerDenunciasPorEstado(estado);
        return new ResponseEntity<>(denuncias, HttpStatus.OK);
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<Denuncia>> obtenerDenunciasPorTipo(@PathVariable String tipo) {
        List<Denuncia> denuncias = denunciaService.obtenerDenunciasPorTipo(tipo);
        return new ResponseEntity<>(denuncias, HttpStatus.OK);
    }
}