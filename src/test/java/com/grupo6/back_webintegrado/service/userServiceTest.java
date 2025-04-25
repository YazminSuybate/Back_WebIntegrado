package com.grupo6.back_webintegrado.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import com.grupo6.back_webintegrado.model.entity.Usuario;
import com.grupo6.back_webintegrado.repository.UserRepository;
import com.grupo6.back_webintegrado.services.impl.UserServiceIMPL;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class userServiceTest {

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

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceIMPL userServiceIMPL;

    @DisplayName("Test para guardar usuario (Service)")
    @Test
    void testSaveUser(){
        //given
        given(userRepository.findById(usuario.getId()))
                .willReturn(Optional.empty());
        given(userRepository.save(usuario)).willReturn(usuario);

        //when
        Usuario userSave = userServiceIMPL.saveUser(usuario);

        //then
        assertThat(userSave).isNotNull();

    }

    @DisplayName("Test para listar a los empleados")
    @Test
    void testListUsers(){
         Usuario usuario1 = Usuario.builder()
                 .nombre("Alonso")
                 .apellido("Valerio")
                 .correo("Valerio@gmail.com")
                 .password("1234")
                 .roles(Collections.emptyList())
                 .build();
         given(userRepository.findAll()).willReturn(List.of(usuario, usuario1));

         List<Usuario> usuario = userServiceIMPL.getAllUsers();

         assertThat(usuario).isNotNull();
         assertThat(usuario.size()).isEqualTo(2);
    }
}
