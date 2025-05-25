package fusion.fusion.controller.controladorRestante;


import fusion.fusion.entity.entidadesRestantes.Archivo;
import fusion.fusion.entity.entidadesRestantes.Denuncia;
import fusion.fusion.service.serviceRestante.ArchivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/archivos")
public class ArchivoController {

    private final ArchivoService archivoService;
    private final String UPLOAD_DIR = "uploads/";

    @Autowired
    public ArchivoController(ArchivoService archivoService) {
        this.archivoService = archivoService;
        // Crear directorio de uploads si no existe
        try {
            Files.createDirectories(Paths.get(UPLOAD_DIR));
        } catch (IOException e) {
            throw new RuntimeException("No se pudo crear el directorio de uploads", e);
        }
    }

    @PostMapping
    public ResponseEntity<Archivo> crearArchivo(@RequestBody Archivo archivo) {
        Archivo nuevoArchivo = archivoService.guardarArchivo(archivo);
        return new ResponseEntity<>(nuevoArchivo, HttpStatus.CREATED);
    }

    @PostMapping("/upload")
    public ResponseEntity<Archivo> subirArchivo(
            @RequestParam("file") MultipartFile file,
            @RequestParam(value = "denunciaId", required = false) Long denunciaId) {

        try {
            // Generar nombre único para el archivo
            String nombreOriginal = file.getOriginalFilename();
            String extension = "";
            if (nombreOriginal != null && nombreOriginal.contains(".")) {
                extension = nombreOriginal.substring(nombreOriginal.lastIndexOf("."));
            }
            String nombreArchivo = UUID.randomUUID().toString() + extension;

            // Guardar archivo en el sistema de archivos
            Path rutaArchivo = Paths.get(UPLOAD_DIR + nombreArchivo);
            Files.copy(file.getInputStream(), rutaArchivo);

            // Crear entidad Archivo
            Archivo archivo = new Archivo();
            archivo.setNombre(nombreOriginal);
            archivo.setTipo(file.getContentType());
            archivo.setRuta(rutaArchivo.toString());
            archivo.setTamano(file.getSize());

            // Si se proporciona denunciaId, asociar el archivo a la denuncia
            if (denunciaId != null) {
                // Aquí necesitarías obtener la denuncia y asignarla
                // Esto depende de cómo hayas implementado la relación
                // Por simplicidad, solo asignamos el ID por ahora
                Denuncia denuncia = new Denuncia();
                denuncia.setId(denunciaId);
                archivo.setDenuncia(denuncia);
            }

            Archivo archivoGuardado = archivoService.guardarArchivo(archivo);
            return new ResponseEntity<>(archivoGuardado, HttpStatus.CREATED);

        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<Archivo>> obtenerTodosLosArchivos() {
        List<Archivo> archivos = archivoService.obtenerTodosLosArchivos();
        return new ResponseEntity<>(archivos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Archivo> obtenerArchivoPorId(@PathVariable Long id) {
        return archivoService.obtenerArchivoPorId(id)
                .map(archivo -> new ResponseEntity<>(archivo, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarArchivo(@PathVariable Long id) {
        return archivoService.obtenerArchivoPorId(id)
                .map(archivo -> {
                    // Eliminar archivo del sistema de archivos
                    try {
                        Files.deleteIfExists(Paths.get(archivo.getRuta()));
                    } catch (IOException e) {
                        // Log error pero continuar con la eliminación de la BD
                        System.err.println("Error al eliminar el archivo físico: " + e.getMessage());
                    }

                    archivoService.eliminarArchivo(id);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/denuncia/{denunciaId}")
    public ResponseEntity<List<Archivo>> obtenerArchivosPorDenuncia(@PathVariable Long denunciaId) {
        List<Archivo> archivos = archivoService.obtenerArchivosPorDenunciaId(denunciaId);
        return new ResponseEntity<>(archivos, HttpStatus.OK);
    }
}