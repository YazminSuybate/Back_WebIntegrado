package com.grupo6.back_webintegrado.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder

public class UsuarioDTO {
    private long id;
    private String nombre;
    private String apellido;

    public UsuarioDTO(long id, String nombre, String apellido) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
    }
}
