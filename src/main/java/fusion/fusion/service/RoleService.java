package fusion.fusion.service;

import fusion.fusion.entity.RoleEntity;
import fusion.fusion.entity.UserEntity;
import fusion.fusion.repository.RoleRepository;
import fusion.fusion.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Set;

/**
 * NUEVA CLASE: Servicio para gestionar roles
 * Proporciona métodos para asignar y revocar roles a usuarios
 */
@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    /**
     * Obtiene todos los roles disponibles
     */
    public List<RoleEntity> getAllRoles() {
        return roleRepository.findAll();
    }

    /**
     * Asigna un rol a un usuario
     */
    public void assignRoleToUser(String email, String roleName) {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado: " + email));

        RoleEntity role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado: " + roleName));

        Set<RoleEntity> userRoles = user.getRoles();
        userRoles.add(role);
        user.setRoles(userRoles);

        userRepository.save(user);
    }

    /**
     * Revoca un rol de un usuario
     */
    public void revokeRoleFromUser(String email, String roleName) {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado: " + email));

        RoleEntity role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado: " + roleName));

        Set<RoleEntity> userRoles = user.getRoles();
        userRoles.remove(role);
        user.setRoles(userRoles);

        userRepository.save(user);
    }

    /**
     * Verifica si un usuario tiene un rol específico
     */
    public boolean hasRole(String email, String roleName) {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado: " + email));

        return user.getRoles().stream()
                .anyMatch(role -> role.getName().equals(roleName));
    }
}