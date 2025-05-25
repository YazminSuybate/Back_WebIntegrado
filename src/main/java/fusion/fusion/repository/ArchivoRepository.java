package fusion.fusion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fusion.fusion.entity.Archivo;

import java.util.List;

@Repository
public interface ArchivoRepository extends JpaRepository<Archivo, Long> {

    // Buscar por denuncia
    List<Archivo> findByDenunciaId(Long denunciaId);

    // Contar archivos por denuncia
    Long countByDenunciaId(Long denunciaId);

    // Eliminar todos los archivos de una denuncia
    void deleteByDenunciaId(Long denunciaId);

}
