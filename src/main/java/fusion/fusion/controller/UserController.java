package fusion.fusion.controller;

import fusion.fusion.entity.UserEntity;
import fusion.fusion.service.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Obtener todos los usuarios
    @GetMapping
    public ResponseEntity<List<UserEntity>> obtenerTodosLosUsuarios() {
        return ResponseEntity.ok(userService.obtenerTodosLosUsuarios());
    }

    // Obtener usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> obtenerUsuarioPorId(@PathVariable Long id) {
        Optional<UserEntity> usuario = userService.obtenerUsuarioPorId(id);
        return usuario.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }

    // Crear nuevo usuario
    @PostMapping
    public ResponseEntity<UserEntity> crearUsuario(@RequestBody UserEntity userEntity) {
        UserEntity nuevoUsuario = userService.crearUsuario(userEntity);
        return ResponseEntity.ok(nuevoUsuario);
    }

    // Actualizar usuario
    @PutMapping("/{id}")
    public ResponseEntity<UserEntity> actualizarUsuario(@PathVariable Long id, @RequestBody UserEntity userEntity) {
        try {
            UserEntity actualizado = userService.actualizarUsuario(id, userEntity);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        userService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }

    // Obtener usuario por email
    @GetMapping("/email")
    public ResponseEntity<UserEntity> obtenerPorEmail(@RequestParam String email) {
        Optional<UserEntity> usuario = userService.obtenerUsuarioPorEmail(email);
        return usuario.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }

    // Verificar si existe usuario por email
    @GetMapping("/existe")
    public ResponseEntity<Boolean> existePorEmail(@RequestParam String email) {
        boolean existe = userService.existeUsuario(email);
        return ResponseEntity.ok(existe);
    }
}
