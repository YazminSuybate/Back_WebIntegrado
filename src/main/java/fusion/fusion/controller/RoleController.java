package fusion.fusion.controller;

import fusion.fusion.entity.RoleEntity;
import fusion.fusion.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/roles")
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @GetMapping
    public ResponseEntity<List<RoleEntity>> getAllRoles() {
        return ResponseEntity.ok(roleService.getAllRoles());
    }

    // MODIFICADO: Ahora acepta un objeto JSON en el cuerpo
    @PostMapping("/assign")
    public ResponseEntity<?> assignRole(@RequestBody RoleRequest request) {
        try {
            roleService.assignRoleToUser(request.getEmail(), request.getRoleName());
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Rol asignado correctamente");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("error", true);
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    // MODIFICADO: Ahora acepta un objeto JSON en el cuerpo
    @PostMapping("/revoke")
    public ResponseEntity<?> revokeRole(@RequestBody RoleRequest request) {
        try {
            roleService.revokeRoleFromUser(request.getEmail(), request.getRoleName());
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Rol revocado correctamente");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("error", true);
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/check")
    public ResponseEntity<?> checkRole(@RequestParam String email, @RequestParam String roleName) {
        boolean hasRole = roleService.hasRole(email, roleName);
        Map<String, Object> response = new HashMap<>();
        response.put("hasRole", hasRole);
        return ResponseEntity.ok(response);
    }
}

// Nueva clase para la solicitud
class RoleRequest {
    private String email;
    private String roleName;

    // Getters y setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}