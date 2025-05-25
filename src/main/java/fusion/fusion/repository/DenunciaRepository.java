package fusion.fusion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fusion.fusion.entity.Denuncia;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DenunciaRepository extends JpaRepository<Denuncia, Long> {
    // Buscar por estado
    List<Denuncia> findByEstado(String estado);

    // Buscar por tipo de denuncia
    List<Denuncia> findByTipoDenuncia(String tipoDenuncia);

    // Buscar por rango de fechas
    List<Denuncia> findByFechaDenunciaBetween(LocalDateTime fechaInicio, LocalDateTime fechaFin);

    // Buscar por tipo y estado
    List<Denuncia> findByTipoDenunciaAndEstado(String tipoDenuncia, String estado);

    // Contar denuncias por tipo
    Long countByTipoDenuncia(String tipoDenuncia);

    // Contar denuncias por estado
    Long countByEstado(String estado);
}


