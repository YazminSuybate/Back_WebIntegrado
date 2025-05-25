package fusion.fusion.repository.repositoryRestante;

import fusion.fusion.entity.entidadesRestantes.SesionPsicologica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SesionPsicologicaRepository extends JpaRepository<SesionPsicologica, Long> {

    // Buscar por paciente
    List<SesionPsicologica> findByPacienteId(Long pacienteId);

    // Buscar por psicólogo
    List<SesionPsicologica> findByPsicologoId(Long psicologoId);

    // Buscar por estado
    List<SesionPsicologica> findByEstado(String estado);

    // Buscar por rango de fechas
    List<SesionPsicologica> findByFechaSesionBetween(LocalDateTime fechaInicio, LocalDateTime fechaFin);

    // Buscar por paciente y estado
    List<SesionPsicologica> findByPacienteIdAndEstado(Long pacienteId, String estado);

    // Buscar por psicólogo y estado
    List<SesionPsicologica> findByPsicologoIdAndEstado(Long psicologoId, String estado);

    // Contar sesiones por psicólogo
    Long countByPsicologoId(Long psicologoId);

    // Contar sesiones por paciente
    Long countByPacienteId(Long pacienteId);

    // Buscar sesiones pendientes (sin psicólogo asignado)
    List<SesionPsicologica> findByPsicologoIsNullAndEstado(String estado);

    // Buscar próximas sesiones
    List<SesionPsicologica> findByFechaSesionAfterAndEstadoOrderByFechaSesionAsc(LocalDateTime fecha, String estado);

}
