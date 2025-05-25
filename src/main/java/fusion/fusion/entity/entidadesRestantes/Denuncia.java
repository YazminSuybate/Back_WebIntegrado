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
@Table(name = "tbl_denuncias")
public class Denuncia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo", length = 100, nullable = false)
    private String titulo;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "fecha_denuncia", nullable = false)
    private LocalDateTime fechaDenuncia;

    @Column(name = "tipo_denuncia", length = 50, nullable = false)
    private String tipoDenuncia; // VIOLENCIA_DOMESTICA, ACOSO, OTRO

    @Column(name = "estado", length = 20, nullable = false)
    private String estado; // REGISTRADA, EN_PROCESO, RESUELTA, ARCHIVADA

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "denunciante_id", nullable = false)
    private UserEntity denunciante;

    @OneToMany(mappedBy = "denuncia", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SeguimientoDenuncia> seguimientos = new ArrayList<>();

    // Relación con archivos (si decides implementarla)
    @OneToMany(mappedBy = "denuncia", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Archivo> archivos = new ArrayList<>();
}