package fusion.fusion.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_seguimientos_asesoria")
public class SeguimientoAsesoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "descripcion", columnDefinition = "TEXT", nullable = false)
    private String descripcion;

    @Column(name = "fecha_seguimiento", nullable = false)
    private LocalDateTime fechaSeguimiento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "asesoria_id", nullable = false)
    private AsesoriaLegal asesoria;
}