package fusion.fusion.entity.entidadesRestantes;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_archivos")
public class Archivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(name = "nombre", nullable = false)
//    private String nombre;

//    @Column(name = "tipo", nullable = false)
//    private String tipo;

    @Column(name = "ruta", nullable = false)
    private String ruta;

//    @Column(name = "tamano")
//    private Long tamano;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "denuncia_id")
    private Denuncia denuncia;
}