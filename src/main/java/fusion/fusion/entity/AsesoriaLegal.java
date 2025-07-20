package fusion.fusion.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_asesorias_legales")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, allowGetters = true)
public class AsesoriaLegal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "fecha_solicitud", nullable = false)
    private LocalDateTime fechaSolicitud;

    @Column(name = "fecha_asesoria")
    private LocalDateTime fechaAsesoria;

    @Column(name = "estado", length = 20, nullable = false)
    private String estado; // PENDIENTE, EN_PROCESO, COMPLETADA, CANCELADA

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "denuncia_id")
    private Denuncia denuncia;
}