package fusion.fusion.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_denuncias")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Denuncia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "fecha_denuncia", nullable = false)
    private LocalDateTime fechaDenuncia;

    @Column(name = "fecha_incidente")
    private LocalDate fechaIncidente;

    @Column(name = "tipo_denuncia", length = 50, nullable = false)
    private String tipoDenuncia; // VIOLENCIA_DOMESTICA, ACOSO, OTRO

    @Column(name = "estado", length = 20, nullable = false)
    private String estado; // REGISTRADA, EN_PROCESO, RESUELTA, ARCHIVADA

    // Relación con archivos (si decides implementarla)
//    @Builder.Default
//    @OneToMany(mappedBy = "denuncia", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Archivo> archivos = new ArrayList<>();
//
//    @Builder.Default
//    @OneToMany(mappedBy = "denuncia", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<AsesoriaLegal> asesoriasLegales = new ArrayList<>();
//
//    @OneToMany(mappedBy = "denuncia", cascade = CascadeType.ALL, orphanRemoval = true)
//    @Builder.Default
//    private List<DenunciaUsuario> usuariosAsignados = new ArrayList<>();

}