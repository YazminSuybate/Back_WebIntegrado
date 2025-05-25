package fusion.fusion.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tbl_denuncia_usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DenunciaUsuario {

    @EmbeddedId
    @Builder.Default
    private DenunciaUsuarioId id = new DenunciaUsuarioId();

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("denunciaId")
    @JoinColumn(name = "denuncia_id")
    private Denuncia denuncia;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("usuarioId")
    @JoinColumn(name = "usuario_id")
    private UserEntity usuario;
}