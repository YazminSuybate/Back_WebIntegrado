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

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.LIST;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // si querés usar tu BD real de test
@EntityScan(basePackages = "com.grupo6.back_webintegrado") // ajustá el paquete si hace falta
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
        userRepository.save(usuario);



    }
}
