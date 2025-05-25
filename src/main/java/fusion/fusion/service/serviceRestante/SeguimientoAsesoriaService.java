package fusion.fusion.service.serviceRestante;


import fusion.fusion.entity.entidadesRestantes.SeguimientoAsesoria;

import java.util.List;
import java.util.Optional;

public interface SeguimientoAsesoriaService {
    fusion.fusion.entity.entidadesRestantes.SeguimientoAsesoria guardarSeguimiento(SeguimientoAsesoria seguimientoAsesoria);
    List<SeguimientoAsesoria> obtenerTodosLosSeguimientos();
    Optional<SeguimientoAsesoria> obtenerSeguimientoPorId(Long id);
    void eliminarSeguimiento(Long id);
    List<SeguimientoAsesoria> obtenerSeguimientosPorAsesoriaId(Long asesoriaId);
}