package fusion.fusion.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiagnosticoPsicologicoDTO {
    private Long sesionId;
    private String recomendaciones;
    private LocalDateTime fechaDiagnostico;
}