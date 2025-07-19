package fusion.fusion.repository;

import fusion.fusion.entity.Denuncia;
import fusion.fusion.entity.DenunciaUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DenunciaUsuarioRepository extends JpaRepository<DenunciaUsuario,Long> {
    @Query("""
    SELECT du
    FROM DenunciaUsuario du
    JOIN FETCH du.denuncia d
    JOIN FETCH du.usuario u
    WHERE u.id = :usuarioId
""")
    List<DenunciaUsuario> findByUsuarioId(@Param("usuarioId") Long usuarioId);

    boolean existsByIdDenunciaIdAndIdUsuarioId(Long denunciaId, Long usuarioId);
    List<DenunciaUsuario> findByDenunciaId(@Param("denunciaId") Long denunciaId);

    @Query("SELECT du.denuncia FROM DenunciaUsuario du WHERE du.usuario.id = :usuarioId")
    List<Denuncia> findDenunciasByUsuarioId(@Param("usuarioId") Long usuarioId);

}
