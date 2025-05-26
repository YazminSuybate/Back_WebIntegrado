package fusion.fusion.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import fusion.fusion.entity.UserEntity;
import fusion.fusion.repository.UserRepository;
import fusion.fusion.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserEntity> obtenerTodosLosUsuarios() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity crearUsuario(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    @Override
    public void eliminarUsuario(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<UserEntity> obtenerUsuarioPorId(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public UserEntity actualizarUsuario(Long id, UserEntity userEntity) {
        return userRepository.findById(id).map(usuarioExistente -> {
            usuarioExistente.setName(userEntity.getName());
            usuarioExistente.setEmail(userEntity.getEmail());
            usuarioExistente.setPassword(userEntity.getPassword());
            usuarioExistente.setVerifyOtp(userEntity.getVerifyOtp());
            usuarioExistente.setIsAccountVerified(userEntity.getIsAccountVerified());
            usuarioExistente.setVerifyOtpExpireAt(userEntity.getVerifyOtpExpireAt());
            usuarioExistente.setResetOtp(userEntity.getResetOtp());
            usuarioExistente.setResetOtpExpireAt(userEntity.getResetOtpExpireAt());
            usuarioExistente.setRoles(userEntity.getRoles());
            return userRepository.save(usuarioExistente);
        }).orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));
    }

    @Override
    public Optional<UserEntity> obtenerUsuarioPorEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public boolean existeUsuario(String email) {
        return userRepository.existsByEmail(email);
    }
}
