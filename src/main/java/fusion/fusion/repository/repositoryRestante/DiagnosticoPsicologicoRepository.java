package fusion.fusion.repository.repositoryRestante;

import fusion.fusion.entity.entidadesRestantes.DiagnosticoPsicologico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DiagnosticoPsicologicoRepository extends JpaRepository<DiagnosticoPsicologico, Long> {

    // Buscar por sesión
    List<DiagnosticoPsicologico> findBySesionId(Long sesionId);

    // Buscar por rango de fechas
    List<DiagnosticoPsicologico> findByFechaDiagnosticoBetween(LocalDateTime fechaInicio, LocalDateTime fechaFin);

    // Buscar por sesión ordenados por fecha (más recientes primero)
    List<DiagnosticoPsicologico> findBySesionIdOrderByFechaDiagnosticoDesc(Long sesionId);

    // Contar diagnósticos por sesión
    Long countBySesionId(Long sesionId);

    // Buscar el último diagnóstico de una sesión
    DiagnosticoPsicologico findFirstBySesionIdOrderByFechaDiagnosticoDesc(Long sesionId);
}
