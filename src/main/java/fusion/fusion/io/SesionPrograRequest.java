package fusion.fusion.io;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SesionPrograRequest {
    private int duracion;
    private LocalDate fecha;
}
