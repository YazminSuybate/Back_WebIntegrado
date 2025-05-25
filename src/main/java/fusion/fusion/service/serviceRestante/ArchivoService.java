package fusion.fusion.service.serviceRestante;

import fusion.fusion.entity.entidadesRestantes.Archivo;

import java.util.List;
import java.util.Optional;

public interface ArchivoService {
    Archivo guardarArchivo(Archivo archivo);
    List<Archivo> obtenerTodosLosArchivos();
    Optional<Archivo> obtenerArchivoPorId(Long id);
    void eliminarArchivo(Long id);
    List<Archivo> obtenerArchivosPorDenunciaId(Long denunciaId);
}