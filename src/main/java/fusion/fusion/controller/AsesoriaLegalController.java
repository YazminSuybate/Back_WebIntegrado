package fusion.fusion.controller;


import fusion.fusion.entity.AsesoriaLegal;
import fusion.fusion.entity.Denuncia;
import fusion.fusion.entity.DenunciaUsuario;
import fusion.fusion.entity.UserEntity;
import fusion.fusion.service.AsesoriaLegalService;

import fusion.fusion.service.DenunciaUsuarioService;
import fusion.fusion.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/asesorias-legales")
public class AsesoriaLegalController {

    private final AsesoriaLegalService asesoriaLegalService;

    @Autowired
    private DenunciaUsuarioService denunciaUsuarioService;

    @Autowired
    private UserService userService;

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






    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<AsesoriaLegal>> obtenerAsesoriasPorEstado(@PathVariable String estado) {
        List<AsesoriaLegal> asesorias = asesoriaLegalService.obtenerAsesoriasPorEstado(estado);
        return new ResponseEntity<>(asesorias, HttpStatus.OK);
    }

    @GetMapping("/denuncia/{denunciaId}")
    public ResponseEntity<List<AsesoriaLegal>> obtenerAsesoriasPorDenuncia(@PathVariable Long denunciaId) {
        List<AsesoriaLegal> asesorias = asesoriaLegalService.obtenerAsesoriasPorDenunciaId(denunciaId);
        return new ResponseEntity<>(asesorias, HttpStatus.OK);
    }














//CREA LA SESORIA SOLO SI TIENE UN ABOGADO RELACIONADO
    @PostMapping("/crear-si-abogado")
    public ResponseEntity<?> crearAsesoriaSiTieneAbogado(@RequestBody Map<String, Object> datos) {
        try {
            Long denunciaId = Long.parseLong(datos.get("denunciaId").toString());
            String descripcion = datos.get("descripcion").toString();

            // Verificar si la denuncia existe
            Optional<Denuncia> optionalDenuncia = asesoriaLegalService.obtenerDenunciaPorId(denunciaId);
            if (optionalDenuncia.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Denuncia no encontrada");
            }

            // Verificar si hay un abogado asignado a la denuncia
            List<UserEntity> usuariosRelacionados = asesoriaLegalService.obtenerUsuariosPorDenuncia(denunciaId);
            boolean tieneAbogado = usuariosRelacionados.stream().anyMatch(usuario ->
                    usuario.getRoles().stream().anyMatch(rol -> rol.getName().equals("LAWYER"))
            );

            if (!tieneAbogado) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Esta denuncia aún no tiene un abogado asignado");
            }

            // Crear asesoría legal
            AsesoriaLegal asesoria = AsesoriaLegal.builder()
                    .descripcion(descripcion)
                    .fechaSolicitud(LocalDateTime.now())
                    .fechaAsesoria(null)
                    .estado("PENDIENTE")
                    .denuncia(optionalDenuncia.get())
                    .build();

            AsesoriaLegal nuevaAsesoria = asesoriaLegalService.guardarAsesoria(asesoria);

            return new ResponseEntity<>(nuevaAsesoria, HttpStatus.CREATED);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear la asesoría legal: " + e.getMessage());
        }
    }











//VERIFICA SI ESA DENUNCIA TIENE UN ABOGADDO
    @PostMapping("/verificar-abogado")
    public ResponseEntity<Map<String, Boolean>> verificarSiTieneAbogado(@RequestBody Map<String, Object> datos) {
        try {
            Long denunciaId = Long.parseLong(datos.get("denunciaId").toString());

            // Verificar si la denuncia existe
            Optional<Denuncia> optionalDenuncia = asesoriaLegalService.obtenerDenunciaPorId(denunciaId);
            if (optionalDenuncia.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("tieneAbogado", false));
            }

            // Verificar si hay un abogado asignado a la denuncia
            List<UserEntity> usuariosRelacionados = asesoriaLegalService.obtenerUsuariosPorDenuncia(denunciaId);
            boolean tieneAbogado = usuariosRelacionados.stream().anyMatch(usuario ->
                    usuario.getRoles().stream().anyMatch(rol -> rol.getName().equals("LAWYER"))
            );

            return ResponseEntity.ok(Map.of("tieneAbogado", tieneAbogado));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("tieneAbogado", false));
        }
    }














    @GetMapping("/abogado/{correo}/asesorias/estado/{estado}")
    public ResponseEntity<?> obtenerAsesoriasDeAbogadoPorEstado(
            @PathVariable String correo,
            @PathVariable String estado) {

        // 1. Obtener al abogado por correo
        Optional<UserEntity> abogadoOpt = userService.obtenerUsuarioPorEmail(correo);
        if (abogadoOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Abogado no encontrado.");
        }

        UserEntity abogado = abogadoOpt.get();

        // 2. Verificar que tenga el rol LAWYER
        List<String> roles = userService.obtenerRolesPorUsuarioId(abogado.getId());
        if (!roles.contains("LAWYER")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El usuario no tiene el rol de abogado.");
        }

        // 3. Obtener relaciones con denuncias
        List<DenunciaUsuario> relaciones = denunciaUsuarioService.ObtenerDenunciaPorUsuario(abogado.getId());
        List<Denuncia> denunciasAsignadas = relaciones.stream()
                .map(DenunciaUsuario::getDenuncia)
                .toList();

        // 4. Obtener asesorías con estado para cada denuncia
        List<AsesoriaLegal> asesoriasFiltradas = new ArrayList<>();
        for (Denuncia denuncia : denunciasAsignadas) {
            List<AsesoriaLegal> asesoriasPorDenunciaYEstado =
                    asesoriaLegalService.obtenerAsesoriasPorDenunciaIdYEstado(denuncia.getId(), estado);
            asesoriasFiltradas.addAll(asesoriasPorDenunciaYEstado);
        }

        return ResponseEntity.ok(asesoriasFiltradas);
    }









}