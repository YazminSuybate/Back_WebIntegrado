package fusion.fusion.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * NUEVA CLASE: Ejemplo de controlador con anotaciones de roles
 * Muestra cómo proteger endpoints usando anotaciones @PreAuthorize
 */
@RestController
public class RoleBasedController {

    // Este endpoint solo es accesible para usuarios con rol ADMIN
    @GetMapping("/admin/dashboard")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> adminDashboard() {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Bienvenido al panel de administración");
        response.put("access", "admin");
        return ResponseEntity.ok(response);
    }

    // Este endpoint solo es accesible para usuarios con rol USER
    @GetMapping("/user/dashboard")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> userDashboard() {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Bienvenido al panel de usuario");
        response.put("access", "user");
        return ResponseEntity.ok(response);
    }

    // Este endpoint es accesible para usuarios con rol ADMIN o MANAGER
    @GetMapping("/reports")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public ResponseEntity<?> reports() {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Acceso a reportes");
        response.put("access", "admin/manager");
        return ResponseEntity.ok(response);
    }

    // Este endpoint requiere ambos roles ADMIN y MANAGER
    @GetMapping("/system/config")
    @PreAuthorize("hasRole('ADMIN') and hasRole('MANAGER')")
    public ResponseEntity<?> systemConfig() {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Configuración del sistema");
        response.put("access", "admin+manager");
        return ResponseEntity.ok(response);
    }
}