package com.grupo6.back_webintegrado.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.grupo6.back_webintegrado.model.entity.Usuario;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;

@WebMvcTest
@AutoConfigureMockMvc
public class LocalControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void findAllUsers() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/get"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$" , Matchers.hasSize(1)));
    }

    @Test
    void RegisterLocal() throws Exception {
        Usuario usuario = Usuario.builder()
                .id(1L)
                .nombre("Edu")
                .apellido("Perez")
                .correo("edu@correo.com")
                .password("123456")
                .roles(new ArrayList<>()) // o null si no querés roles por ahora
                .build();

        mockMvc.perform(MockMvcRequestBuilders.post("/api/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(usuario)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().string("Recurso Creado Exitosamente"));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException("Error al convertir a JSON: " + e.getMessage(), e);
        }
    }


}
