package com.grupo6.back_webintegrado.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DenunciasRegistroDTO {
    private Long idVictima;
    private String descripcion;
    private LocalDateTime fecha;
    private String estado;
}
