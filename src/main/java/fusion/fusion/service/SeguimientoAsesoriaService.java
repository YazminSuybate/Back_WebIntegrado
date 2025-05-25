package fusion.fusion.service;


import java.util.List;
import java.util.Optional;

import fusion.fusion.entity.SeguimientoAsesoria;

public interface SeguimientoAsesoriaService {
    fusion.fusion.entity.SeguimientoAsesoria guardarSeguimiento(SeguimientoAsesoria seguimientoAsesoria);
    List<SeguimientoAsesoria> obtenerTodosLosSeguimientos();
    Optional<SeguimientoAsesoria> obtenerSeguimientoPorId(Long id);
    void eliminarSeguimiento(Long id);
    List<SeguimientoAsesoria> obtenerSeguimientosPorAsesoriaId(Long asesoriaId);
}