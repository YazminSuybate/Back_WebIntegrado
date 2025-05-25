package fusion.fusion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fusion.fusion.entity.SeguimientoAsesoria;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SeguimientoAsesoriaRepository extends JpaRepository<SeguimientoAsesoria, Long> {
    // Buscar por asesoría
    List<SeguimientoAsesoria> findByAsesoriaId(Long asesoriaId);

    // Buscar por rango de fechas
    List<SeguimientoAsesoria> findByFechaSeguimientoBetween(LocalDateTime fechaInicio, LocalDateTime fechaFin);

    // Buscar por asesoría ordenados por fecha (más recientes primero)
    List<SeguimientoAsesoria> findByAsesoriaIdOrderByFechaSeguimientoDesc(Long asesoriaId);

    // Contar seguimientos por asesoría
    Long countByAsesoriaId(Long asesoriaId);

    // Buscar el último seguimiento de una asesoría
    SeguimientoAsesoria findFirstByAsesoriaIdOrderByFechaSeguimientoDesc(Long asesoriaId);

}
