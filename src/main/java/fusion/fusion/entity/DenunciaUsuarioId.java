package fusion.fusion.entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.*;
import lombok.*;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DenunciaUsuarioId implements Serializable {

    @Column(name = "denuncia_id")
    private Long denunciaId;

    @Column(name = "usuario_id")
    private Long usuarioId;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof DenunciaUsuarioId))
            return false;
        DenunciaUsuarioId that = (DenunciaUsuarioId) o;
        return Objects.equals(denunciaId, that.denunciaId) &&
                Objects.equals(usuarioId, that.usuarioId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(denunciaId, usuarioId);
    }
}