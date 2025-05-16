package com.grupo6.back_webintegrado.DTO;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRegistroDTO {
    private String nombre;
    private String apellido;
    private String correo;
    private String password;

}
