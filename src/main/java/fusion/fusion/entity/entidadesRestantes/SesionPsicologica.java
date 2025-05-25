package fusion.fusion.entity.entidadesRestantes;

import fusion.fusion.entity.UserEntity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_sesiones_psicologicas")
public class SesionPsicologica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String titulo;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "fecha_solicitud", nullable = false)
    private LocalDateTime fechaSolicitud;

    @Column(name = "fecha_sesion")
    private LocalDateTime fechaSesion;

    @Column(name = "duracion")
    private Integer duracion; // En minutos

    @Column(name = "estado", length = 20, nullable = false)
    private String estado; // PENDIENTE, EN_PROCESO, COMPLETADA, CANCELADA

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id", nullable = false)
    private UserEntity paciente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "psicologo_id")
    private UserEntity psicologo;

    @OneToMany(mappedBy = "sesion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DiagnosticoPsicologico> diagnosticos = new ArrayList<>();
}