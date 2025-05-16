package com.grupo6.back_webintegrado.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioConsultaDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private String correo;
    private List<String> roles;
}
