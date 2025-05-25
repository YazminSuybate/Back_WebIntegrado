package fusion.fusion.repository.repositoryRestante;

import fusion.fusion.entity.entidadesRestantes.SeguimientoDenuncia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SeguimientoDenunciaRepository extends JpaRepository<SeguimientoDenuncia, Long> {


    // Buscar por denuncia
    List<SeguimientoDenuncia> findByDenunciaId(Long denunciaId);

    // Buscar por rango de fechas
    List<SeguimientoDenuncia> findByFechaSeguimientoBetween(LocalDateTime fechaInicio, LocalDateTime fechaFin);

    // Buscar por denuncia ordenados por fecha (más recientes primero)
    List<SeguimientoDenuncia> findByDenunciaIdOrderByFechaSeguimientoDesc(Long denunciaId);

    // Contar seguimientos por denuncia
    Long countByDenunciaId(Long denunciaId);

    // Buscar el último seguimiento de una denuncia
    SeguimientoDenuncia findFirstByDenunciaIdOrderByFechaSeguimientoDesc(Long denunciaId);
}
