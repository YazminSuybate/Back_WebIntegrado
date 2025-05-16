package com.grupo6.back_webintegrado.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AsesoriaLegalRegistroDTO {

    private String empleado;
    private String usuario;
    private String fecha;
    private String tema;
    private String resumen;
}
