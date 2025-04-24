package com.grupo6.back_webintegrado.service;

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

import java.util.Collections;
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


        //then


    }


}
