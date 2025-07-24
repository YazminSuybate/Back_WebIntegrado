package fusion.fusion.controller;


import fusion.fusion.entity.Denuncia;
import fusion.fusion.entity.DenunciaUsuario;
import fusion.fusion.entity.UserEntity;
import fusion.fusion.io.DenunciaRequest;
import fusion.fusion.service.DenunciaService;

import fusion.fusion.service.DenunciaUsuarioService;
import fusion.fusion.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/api/denuncias")
public class DenunciaController {

    private final DenunciaService denunciaService;
    @Autowired
    private UserService userService;
    @Autowired
    private DenunciaUsuarioService denunciaUsuarioService;

    public DenunciaController(DenunciaService denunciaService) {
        this.denunciaService = denunciaService;
    }

    @PostMapping("/{correo}")
    public ResponseEntity<Denuncia> crearDenuncia(@RequestBody DenunciaRequest request, @PathVariable String correo) {

        Denuncia denuncia = new Denuncia();
        denuncia.setDescripcion(request.getDescripcion());
        denuncia.setTipoDenuncia(request.getTipo());
        denuncia.setFechaIncidente(request.getFecha_incidente());
        denuncia.setFechaDenuncia(LocalDateTime.now());
        denuncia.setEstado("activo");

        denunciaService.guardarDenuncia(denuncia);
        Optional<UserEntity> user = userService.obtenerUsuarioPorEmail(correo);

        DenunciaUsuario denunciaUsuario = new DenunciaUsuario();
        denunciaUsuario.setUsuario(user.get());
        denunciaUsuario.setDenuncia(denuncia);

        denunciaUsuarioService.CrearDenunciausuario(denunciaUsuario);

        return new ResponseEntity<>(denuncia, HttpStatus.CREATED);
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


//metodo que relaciona el abogado con la denuncia hecha por un usuario
@PostMapping("/asignar/{denunciaId}/{correoAbogado}")
public ResponseEntity<?> asignarDenunciaAAbogado(
        @PathVariable Long denunciaId,
        @PathVariable String correoAbogado) {

    // 1. Obtener la denuncia
    Optional<Denuncia> denunciaOpt = denunciaService.obtenerDenunciaPorId(denunciaId);
    if (denunciaOpt.isEmpty()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Denuncia no encontrada.");
    }

    Denuncia denuncia = denunciaOpt.get();

    // 2. Obtener todos los usuarios relacionados a la denuncia
    List<DenunciaUsuario> relacionados = denunciaUsuarioService.obtenerUsuariosPorDenuncia(denunciaId);

    // 3. Verificar si alguno de los usuarios tiene el rol LAWYER
    for (DenunciaUsuario du : relacionados) {
        UserEntity usuario = du.getUsuario();
        List<String> roles = userService.obtenerRolesPorUsuarioId(usuario.getId());
        if (roles.contains("LAWYER")) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("La denuncia ya ha sido asignada a un abogado.");
        }
    }

    // 4. Obtener el abogado a asignar
    Optional<UserEntity> abogadoOpt = userService.obtenerUsuarioPorEmail(correoAbogado);
    if (abogadoOpt.isEmpty()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Abogado no encontrado.");
    }

    UserEntity abogado = abogadoOpt.get();

    // 5. Verificar que el usuario que se intenta asignar tenga el rol LAWYER
    List<String> rolesAbogado = userService.obtenerRolesPorUsuarioId(abogado.getId());
    if (!rolesAbogado.contains("LAWYER")) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("El usuario no tiene el rol de abogado.");
    }

    // 6. Crear la relación denuncia-abogado
    DenunciaUsuario relacion = new DenunciaUsuario();
    relacion.setDenuncia(denuncia);
    relacion.setUsuario(abogado);
    denunciaUsuarioService.CrearDenunciausuario(relacion);



    // ✅ 4. Actualizar estado de la denuncia
    denuncia.setEstado("asignada"); // o "en_proceso", como desees
    denunciaService.guardarDenuncia(denuncia);



    return ResponseEntity.status(HttpStatus.CREATED).body("Abogado asignado exitosamente.");
}


//metodo que relaciona al psicologo con la denuncia hecha por un usuario
@PostMapping("/asignarPsico/{denunciaId}/{correoAbogado}")
    public ResponseEntity<?> asignarDenunciaAPsico(@PathVariable Long denunciaId, @PathVariable String correoPsicologo) {

        // 1. Obtener la denuncia
        Optional<Denuncia> denunciaOpt = denunciaService.obtenerDenunciaPorId(denunciaId);
        if (denunciaOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Denuncia no encontrada.");
        }

        Denuncia denuncia = denunciaOpt.get();

        // 2. Obtener todos los usuarios relacionados a la denuncia
        List<DenunciaUsuario> relacionados = denunciaUsuarioService.obtenerUsuariosPorDenuncia(denunciaId);

        // 3. Verificar si alguno de los usuarios tiene el rol LAWYER
        for (DenunciaUsuario du : relacionados) {
            UserEntity usuario = du.getUsuario();
            List<String> roles = userService.obtenerRolesPorUsuarioId(usuario.getId());
            if (roles.contains("PSYCHOLOGIST")) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("La denuncia ya ha sido asignada a un PSICOLOGO.");
            }
        }

        // 4. Obtener el psicologo a asignar
        Optional<UserEntity> psicoOpt = userService.obtenerUsuarioPorEmail(correoPsicologo);
        if (psicoOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Psicologo no encontrado.");
        }

        UserEntity psicologo = psicoOpt.get();

        // 5. Verificar que el usuario que se intenta asignar tenga el rol LAWYER
        List<String> rolesPsico = userService.obtenerRolesPorUsuarioId(psicologo.getId());
        if (!rolesPsico.contains("PSYCHOLOGIST")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("El usuario no tiene el rol de psicologo.");
        }

        // 6. Crear la relación denuncia-abogado
        DenunciaUsuario relacion = new DenunciaUsuario();
        relacion.setDenuncia(denuncia);
        relacion.setUsuario(psicologo);
        denunciaUsuarioService.CrearDenunciausuario(relacion);


        return ResponseEntity.status(HttpStatus.CREATED).body("Psicologo asignado exitosamente.");
    }






//OBTENER DENUNCIAS ACTIVAS
    @GetMapping("/activas")
    public ResponseEntity<List<Denuncia>> obtenerDenunciasActivas() {
        List<Denuncia> denunciasActivas = denunciaService.obtenerDenunciasPorEstado("activo");
        return new ResponseEntity<>(denunciasActivas, HttpStatus.OK);
    }



    //OBTENER DENUNCIAS aprobadas OSEA SIN ASIGNAR A ABOGADOS
    @GetMapping("/aprobadas")
    public ResponseEntity<List<Denuncia>> obtenerDenunciasAprobadas() {
        List<Denuncia> denunciasActivas = denunciaService.obtenerDenunciasPorEstado("aprobada");
        return new ResponseEntity<>(denunciasActivas, HttpStatus.OK);
    }








    @GetMapping("/abogado/{correo}")
    public ResponseEntity<?> obtenerDenunciasAsignadasAAbogado(@PathVariable String correo) {
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

        // 4. Extraer denuncias
        List<Denuncia> denunciasAsignadas = relaciones.stream()
                .map(DenunciaUsuario::getDenuncia)
                .toList();

        return ResponseEntity.ok(denunciasAsignadas);
    }



    // me todo para cambiar el estado de una denuncia

    @PutMapping("/estadoCambio/{id}")
    public ResponseEntity<?> actualizarEstadoDenuncia(
            @PathVariable Long id,
            @RequestParam String nuevoEstado) {
        try {
            Optional<Denuncia> optionalDenuncia = denunciaService.obtenerDenunciaPorId(id);
            if (optionalDenuncia.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Denuncia no encontrada");
            }

            Denuncia denuncia = optionalDenuncia.get();
            denuncia.setEstado(nuevoEstado);
            denunciaService.guardarDenuncia(denuncia);

            return ResponseEntity.ok("Estado actualizado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al actualizar el estado: " + e.getMessage());
        }
    }


    @GetMapping("/usuarios-relacionados/{denunciaId}")
    public ResponseEntity<?> obtenerDenuncianteUsuario(@PathVariable Long denunciaId) {
        try {
            List<DenunciaUsuario> relacionados = denunciaUsuarioService.obtenerUsuariosPorDenuncia(denunciaId);

            if (relacionados.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No hay usuarios relacionados a esta denuncia.");
            }

            for (DenunciaUsuario relacion : relacionados) {
                UserEntity usuario = relacion.getUsuario();
                List<String> roles = userService.obtenerRolesPorUsuarioId(usuario.getId());

                if (roles.size() == 1 && roles.contains("USER")) {
                    Map<String, Object> datosUsuario = new HashMap<>();
                    datosUsuario.put("id", usuario.getId());
                    datosUsuario.put("nombre", usuario.getName());
                    datosUsuario.put("correo", usuario.getEmail());
                    datosUsuario.put("roles", roles);
                    return ResponseEntity.ok(datosUsuario);
                }
            }

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontró un usuario con único rol USER.");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener el denunciante: " + e.getMessage());
        }
    }



















}