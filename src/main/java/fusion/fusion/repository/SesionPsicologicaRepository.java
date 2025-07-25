package fusion.fusion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fusion.fusion.entity.SesionPsicologica;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SesionPsicologicaRepository extends JpaRepository<SesionPsicologica, Long> {

    // Buscar por estado
    List<SesionPsicologica> findByEstado(String estado);

    // Buscar por rango de fechas
    List<SesionPsicologica> findByFechaSesionBetween(LocalDateTime fechaInicio, LocalDateTime fechaFin);

    // Buscar próximas sesiones
    List<SesionPsicologica> findByFechaSesionAfterAndEstadoOrderByFechaSesionAsc(LocalDateTime fecha, String estado);

    List<SesionPsicologica> findByDenunciaIdAndEstado(Long denunciaId, String estado);


}
