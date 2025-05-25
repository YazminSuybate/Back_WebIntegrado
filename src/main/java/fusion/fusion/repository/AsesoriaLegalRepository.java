package fusion.fusion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fusion.fusion.entity.AsesoriaLegal;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AsesoriaLegalRepository extends JpaRepository<AsesoriaLegal, Long> {
    // Buscar por estado
    List<AsesoriaLegal> findByEstado(String estado);

    // Buscar por rango de fechas
    List<AsesoriaLegal> findByFechaAsesoriaBetween(LocalDateTime fechaInicio, LocalDateTime fechaFin);
}