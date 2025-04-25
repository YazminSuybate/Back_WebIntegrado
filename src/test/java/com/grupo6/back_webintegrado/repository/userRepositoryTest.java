package com.grupo6.back_webintegrado.repository;

import com.grupo6.back_webintegrado.model.entity.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


// Anotación para pruebas de repositorios JPA, utiliza una BD en memoria para testeo
@DataJpaTest
// Escanea las entidades en el paquete indicado
@EntityScan(basePackages = "com.grupo6.back_webintegrado")
public class userRepositoryTest {

    // Inyectamos el repositorio que vamos a probar
    @Autowired
    private UserRepository userRepository;

    // Usuario que se usará como dato común en varias pruebas
    private Usuario usuario;

    // Este método se ejecuta antes de cada prueba
    @BeforeEach
    void setup(){
        // Se crea un objeto usuario con datos por defecto
        usuario = Usuario.builder()
                .nombre("Jhoel")
                .apellido("Tini")
                .correo("Jhoel@gmail.com")
                .password("123456")
                .roles(Collections.emptyList()) // Lista vacía de roles
                .build();
    }

    // Prueba para verificar que se puede guardar un usuario en la base de datos
    @Test
    @DisplayName("Test de crear usuario")
    void testSaveUser(){
        // given - Se crea un nuevo usuario
        Usuario usuario1 = Usuario.builder()
                .nombre("Edu")
                .apellido("Perez")
                .correo("Edu@gmail.com")
                .password("1234")
                .roles(Collections.emptyList())
                .build();

        // when - Se guarda el usuario
        Usuario userSave = userRepository.save(usuario1);

        // then - Se verifica que no sea nulo y que tenga un ID asignado
        assertThat(userSave).isNotNull();
        assertThat(userSave.getId()).isGreaterThan(0);
    }

    // Prueba para verificar que se pueden traer todos los usuarios
    @Test
    @DisplayName("Test de traer usuarios")
    void tesPullUsers(){
        // given - Se guardan dos usuarios
        Usuario usuario1 = Usuario.builder()
                .nombre("Fabrizio")
                .apellido("Luis")
                .correo("Fabrizio@gmail.com")
                .password("12345")
                .roles(Collections.emptyList())
                .build();

        userRepository.save(usuario1);
        userRepository.save(usuario);

        // when - Se obtiene la lista de todos los usuarios
        List<Usuario> listUsers = userRepository.findAll();

        // then - Se verifica que la lista no esté vacía y tenga dos elementos
        assertThat(listUsers).isNotNull();
        assertThat(listUsers.size()).isEqualTo(2);
    }

    // Prueba para traer un usuario por su ID
    @Test
    @DisplayName("Test para traer usuario por ID")
    void testUserId() {
        // given - Se guarda el usuario
        userRepository.save(usuario);

        // when - Se busca por su ID
        Usuario usuarioBD = userRepository.findById(usuario.getId()).get();

        // then - Se verifica que no sea nulo
        assertThat(usuarioBD).isNotNull();
    }

    // Prueba para actualizar un usuario
    @Test
    @DisplayName("Test para actualizar usuario")
    void testUpdateUser(){
        // given - Se guarda el usuario
        userRepository.save(usuario);

        // when - Se obtiene el usuario, se modifican sus datos y se vuelve a guardar
        Usuario usuarioBD = userRepository.findById(usuario.getId()).get();
        usuarioBD.setNombre("Lincolm");
        usuarioBD.setApellido("Santino");
        usuarioBD.setCorreo("tiburoncin@gmail.com");
        usuarioBD.setPassword("123");

        Usuario userNew = userRepository.save(usuarioBD);

        // then - Se verifica que los cambios hayan sido guardados correctamente
        assertThat(userNew.getCorreo()).isEqualTo("tiburoncin@gmail.com");
        assertThat(userNew.getNombre()).isEqualTo("Lincolm");
    }

    // Prueba para eliminar un usuario
    @Test
    @DisplayName("Test para eliminar un usuario")
    void testDeleteUser () {
        // given - Se guarda el usuario
        userRepository.save(usuario);

        // when - Se elimina por su ID
        userRepository.deleteById(usuario.getId());

        // Se intenta buscar nuevamente
        Optional<Usuario> userOptional = userRepository.findById(usuario.getId());

        // then - Se espera que el usuario ya no exista
        assertThat(userOptional).isEmpty();
    }
}
