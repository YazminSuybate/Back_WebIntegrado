package fusion.fusion.repository.repositoryRestante;

import fusion.fusion.entity.entidadesRestantes.AsesoriaLegal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AsesoriaLegalRepository extends JpaRepository<AsesoriaLegal, Long> {
    // Buscar por cliente
    List<AsesoriaLegal> findByClienteId(Long clienteId);

    // Buscar por abogado
    List<AsesoriaLegal> findByAbogadoId(Long abogadoId);

    // Buscar por estado
    List<AsesoriaLegal> findByEstado(String estado);

    // Buscar por rango de fechas
    List<AsesoriaLegal> findByFechaAsesoriaBetween(LocalDateTime fechaInicio, LocalDateTime fechaFin);

    // Buscar por cliente y estado
    List<AsesoriaLegal> findByClienteIdAndEstado(Long clienteId, String estado);

    // Buscar por abogado y estado
    List<AsesoriaLegal> findByAbogadoIdAndEstado(Long abogadoId, String estado);

    // Contar asesorías por abogado
    Long countByAbogadoId(Long abogadoId);

    // Contar asesorías por cliente
    Long countByClienteId(Long clienteId);

    // Buscar asesorías pendientes (sin abogado asignado)
    List<AsesoriaLegal> findByAbogadoIsNullAndEstado(String estado);
}

