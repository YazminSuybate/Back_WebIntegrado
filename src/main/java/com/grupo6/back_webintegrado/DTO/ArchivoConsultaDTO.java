package com.grupo6.back_webintegrado.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArchivoConsultaDTO {
    private long id;
    private String nombreArchi;
    private String ruta;
    private String tipo;
    private LocalDateTime fecha;
    private Long idDenuncia;
}
