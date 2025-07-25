package fusion.fusion.controller;

import fusion.fusion.entity.*;
import fusion.fusion.io.SesionPrograRequest;
import fusion.fusion.io.SesionRequest;
import fusion.fusion.service.*;

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
@RequestMapping("/api/sesiones-psicologicas")
public class SesionPsicologicaController {

    @Autowired
    private DenunciaService denunciaService;
    private final SesionPsicologicaService sesionPsicologicaService;

    public SesionPsicologicaController(SesionPsicologicaService sesionPsicologicaService) {
        this.sesionPsicologicaService = sesionPsicologicaService;
    }

@Autowired
    private  AsesoriaLegalService asesoriaLegalService;

    @Autowired
    private UserService userService;

    @Autowired
    private DenunciaUsuarioService denunciaUsuarioService;
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


       @PostMapping("programar/{sesionId}")
    public ResponseEntity<SesionPsicologica> programarSesion(@PathVariable Long sesionId,@RequestBody SesionPrograRequest sesionRequest) {

        SesionPsicologica sesionPsicologica = sesionPsicologicaService.obtenerSesionPorId(sesionId).get();
        sesionPsicologica.setFechaSesion(sesionRequest.getFecha());
        sesionPsicologica.setDuracion(sesionRequest.getDuracion());
        sesionPsicologica.setEstado("PROGRAMADA");

        sesionPsicologicaService.guardarSesion(sesionPsicologica);

        return new ResponseEntity<>(sesionPsicologica, HttpStatus.CREATED);
    }









    //VERIFICA SI ESA DENUNCIA TIENE UN ABOGADDO
    @PostMapping("/verificar-psicologo")
    public ResponseEntity<Map<String, Boolean>> verificarSiTienePsicologo(@RequestBody Map<String, Object> datos) {
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
                    usuario.getRoles().stream().anyMatch(rol -> rol.getName().equals("PSYCHOLOGIST"))
            );

            return ResponseEntity.ok(Map.of("tieneAbogado", tieneAbogado));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("tieneAbogado", false));
        }
    }














    @PostMapping("/crear-si-psicologo")
    public ResponseEntity<?> crearSesionSiTienePsicologo(@RequestBody Map<String, Object> datos) {
        try {
            Long denunciaId = Long.parseLong(datos.get("denunciaId").toString());
            String descripcion = datos.get("descripcion").toString();

            // Verificar si la denuncia existe
            Optional<Denuncia> optionalDenuncia = asesoriaLegalService.obtenerDenunciaPorId(denunciaId);
            if (optionalDenuncia.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Denuncia no encontrada");
            }

            // Verificar si hay un psicólogo asignado a la denuncia
            List<UserEntity> usuariosRelacionados = asesoriaLegalService.obtenerUsuariosPorDenuncia(denunciaId);
            boolean tienePsicologo = usuariosRelacionados.stream().anyMatch(usuario ->
                    usuario.getRoles().stream().anyMatch(rol -> rol.getName().equals("PSYCHOLOGIST"))
            );

            if (!tienePsicologo) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Esta denuncia aún no tiene un psicólogo asignado");
            }

            // Crear sesión psicológica
            SesionPsicologica sesion = SesionPsicologica.builder()
                    .descripcion(descripcion)
                    .fechaSolicitud(LocalDateTime.now())
                    .fechaSesion(null)
                    .duracion(null)
                    .estado("PENDIENTE")
                    .denuncia(optionalDenuncia.get())
                    .build();

            SesionPsicologica nuevaSesion = sesionPsicologicaService.guardarSesion(sesion);

            return new ResponseEntity<>(nuevaSesion, HttpStatus.CREATED);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear la sesión psicológica: " + e.getMessage());
        }
    }














    @GetMapping("/psicologo/{correo}/sesiones/estado/{estado}")
    public ResponseEntity<?> obtenerSesionesDePsicologoPorEstado(
            @PathVariable String correo,
            @PathVariable String estado) {

        // 1. Obtener al psicólogo por correo
        Optional<UserEntity> psicologoOpt = userService.obtenerUsuarioPorEmail(correo);
        if (psicologoOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Psicólogo no encontrado.");
        }

        UserEntity psicologo = psicologoOpt.get();

        // 2. Verificar que tenga el rol PSYCHOLOGIST
        List<String> roles = userService.obtenerRolesPorUsuarioId(psicologo.getId());
        if (!roles.contains("PSYCHOLOGIST")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El usuario no tiene el rol de psicólogo.");
        }

        // 3. Obtener relaciones con denuncias
        List<DenunciaUsuario> relaciones = denunciaUsuarioService.ObtenerDenunciaPorUsuario(psicologo.getId());
        List<Denuncia> denunciasAsignadas = relaciones.stream()
                .map(DenunciaUsuario::getDenuncia)
                .toList();

        // 4. Obtener sesiones psicológicas con estado para cada denuncia
        List<SesionPsicologica> sesionesFiltradas = new ArrayList<>();
        for (Denuncia denuncia : denunciasAsignadas) {
            List<SesionPsicologica> sesionesPorEstado =
                    sesionPsicologicaService.obtenerSesionesPorDenunciaIdYEstado(denuncia.getId(), estado);
            sesionesFiltradas.addAll(sesionesPorEstado);
        }

        return ResponseEntity.ok(sesionesFiltradas);
    }












}