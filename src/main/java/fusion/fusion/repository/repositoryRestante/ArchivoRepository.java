package fusion.fusion.repository.repositoryRestante;

import fusion.fusion.entity.entidadesRestantes.Archivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArchivoRepository extends JpaRepository<Archivo, Long> {

    // Buscar por denuncia
    List<Archivo> findByDenunciaId(Long denunciaId);

    // Buscar por tipo
    List<Archivo> findByTipo(String tipo);

    // Buscar por nombre (contiene)
    List<Archivo> findByNombreContaining(String nombre);

    // Contar archivos por denuncia
    Long countByDenunciaId(Long denunciaId);

    // Contar archivos por tipo
    Long countByTipo(String tipo);

    // Buscar por denuncia y tipo
    List<Archivo> findByDenunciaIdAndTipo(Long denunciaId, String tipo);

    // Eliminar todos los archivos de una denuncia
    void deleteByDenunciaId(Long denunciaId);

}
