package fusion.fusion.service.serviceRestante;

import fusion.fusion.entity.entidadesRestantes.SeguimientoDenuncia;

import java.util.List;
import java.util.Optional;

public interface SeguimientoDenunciaService {
    SeguimientoDenuncia guardarSeguimiento(SeguimientoDenuncia seguimientoDenuncia);
    List<SeguimientoDenuncia> obtenerTodosLosSeguimientos();
    Optional<SeguimientoDenuncia> obtenerSeguimientoPorId(Long id);
    void eliminarSeguimiento(Long id);
    List<SeguimientoDenuncia> obtenerSeguimientosPorDenunciaId(Long denunciaId);
}