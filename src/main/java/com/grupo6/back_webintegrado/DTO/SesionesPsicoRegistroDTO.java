package com.grupo6.back_webintegrado.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SesionesPsicoRegistroDTO {
    private long idPsicologo;
    private long idVictima;
    private LocalDateTime fecha;
    private int duracion;
    private String observaciones;
}
