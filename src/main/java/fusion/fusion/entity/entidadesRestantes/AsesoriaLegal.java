package fusion.fusion.entity.entidadesRestantes;

import fusion.fusion.entity.UserEntity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_asesorias_legales")
public class AsesoriaLegal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String titulo;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "fecha_solicitud", nullable = false)
    private LocalDateTime fechaSolicitud;

    @Column(name = "fecha_asesoria")
    private LocalDateTime fechaAsesoria;

    @Column(name = "estado", length = 20, nullable = false)
    private String estado; // PENDIENTE, EN_PROCESO, COMPLETADA, CANCELADA

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private UserEntity cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "abogado_id")
    private UserEntity abogado;
}