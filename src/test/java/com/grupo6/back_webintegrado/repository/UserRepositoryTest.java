package com.grupo6.back_webintegrado.repository;

import com.grupo6.back_webintegrado.model.entity.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.OPTIONAL;

@DataJpaTest
@EntityScan(basePackages = "com.grupo6.back_webintegrado")
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private Usuario usuario;

    @BeforeEach
    void setup(){
        usuario = Usuario.builder()
                .nombre("Jhoel")
                .apellido("Tini")
                .correo("Jhoel@gmail.com")
                .password("123456")
                .roles(Collections.emptyList())
                .build();
    }

    @Test
    @DisplayName("Test de crear usuario")
    void testSaveUser(){
        //given - dado o condición previa o configuración
        Usuario usuario1 = Usuario.builder()
                .nombre("Edu")
                .apellido("Perez")
                .correo("Edu@gmail.com")
                .password("1234")
                .roles(Collections.emptyList())
                .build();
        //when - acción o el comportamiento que vamos a probar
        Usuario userSave = userRepository.save(usuario1);

        //then - verificar la salida
        assertThat(userSave).isNotNull();
        assertThat(userSave.getId()).isGreaterThan(0);
    }

    @Test
    @DisplayName("Test de traer usuarios")
    void tesPullUsers(){
        //given
        Usuario usuario1 = Usuario.builder()
                .nombre("Fabrizio")
                .apellido("Luis")
                .correo("Fabrizio@gmail.com")
                .password("12345")
                .roles(Collections.emptyList())
                .build();

        userRepository.save(usuario1);
        userRepository.save(usuario);
        //when
        List<Usuario> listUsers = userRepository.findAll();

        //then
        assertThat(listUsers).isNotNull();
        assertThat(listUsers.size()).isEqualTo(2);

    }

    @Test
    @DisplayName("Test para traer usuario por ID")
    void testUserId(){
        //given
        userRepository.save(usuario);

        //when
        Usuario usuarioBD = userRepository.findById(usuario.getId()).get();

        //then

        assertThat(usuarioBD).isNotNull();

    }


    @Test
    @DisplayName("Test para actualizar usuario")
    void testUpdateUser(){
        //given
        userRepository.save(usuario);

        //when
        Usuario usuarioBD = userRepository.findById(usuario.getId()).get();
        usuarioBD.setNombre("Lincolm");
        usuarioBD.setApellido("Santino");
        usuarioBD.setCorreo("tiburoncin@gmail.com");
        usuarioBD.setPassword("123");

        Usuario userNew = userRepository.save(usuarioBD);

        //then
        assertThat(userNew.getCorreo()).isEqualTo("tiburoncin@gmail.com");
        assertThat(userNew.getNombre()).isEqualTo("Lincolm");

    }

    @Test
    @DisplayName("Test para eliminar un usuario")
    void testDeleteUser () {
        //given
        userRepository.save(usuario);

        //when
        userRepository.deleteById(usuario.getId());

        Optional<Usuario> userOptional = userRepository.findById(usuario.getId());

        //then
        assertThat(userOptional).isEmpty();
    }
}
