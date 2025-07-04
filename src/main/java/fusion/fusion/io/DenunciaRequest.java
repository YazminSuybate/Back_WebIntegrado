package fusion.fusion.io;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DenunciaRequest {

    private String descripcion;
    private LocalDate fecha_incidente;
    private String tipo;

}
