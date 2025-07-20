package fusion.fusion.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeguimientoAsesoriaDTO {
    private String descripcion;
    private LocalDateTime fechaSeguimiento;
    private Long asesoriaId;
}