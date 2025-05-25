package fusion.fusion.service;

import java.util.List;
import java.util.Optional;

import fusion.fusion.entity.Archivo;

public interface ArchivoService {
    Archivo guardarArchivo(Archivo archivo);
    List<Archivo> obtenerTodosLosArchivos();
    Optional<Archivo> obtenerArchivoPorId(Long id);
    void eliminarArchivo(Long id);
    List<Archivo> obtenerArchivosPorDenunciaId(Long denunciaId);
}