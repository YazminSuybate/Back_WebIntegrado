package com.grupo6.back_webintegrado.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.grupo6.back_webintegrado.model.dto.UsuarioDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest
@AutoConfigureMockMvc
public class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void RegisterLocal() throws Exception {
        UsuarioDTO usuario = new UsuarioDTO(1L, "Edu", "Perez");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/recurso/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(usuario)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().string("Creado Exitosamente"));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException("Error al convertir a JSON: " + e.getMessage(), e);
        }
    }
}
