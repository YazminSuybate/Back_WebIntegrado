package fusion.fusion.service;

import java.util.List;
import java.util.Optional;

import fusion.fusion.entity.UserEntity;

public interface UserService {
    List<UserEntity> obtenerTodosLosUsuarios();

    UserEntity crearUsuario(UserEntity userEntity);

    void eliminarUsuario(Long id);

    Optional<UserEntity> obtenerUsuarioPorId(Long id);

    UserEntity actualizarUsuario(Long id, UserEntity userEntity);

    Optional<UserEntity> obtenerUsuarioPorEmail(String email);

    boolean existeUsuario(String email);
}