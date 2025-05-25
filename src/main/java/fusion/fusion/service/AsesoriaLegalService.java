package fusion.fusion.service;


import java.util.List;
import java.util.Optional;

import fusion.fusion.entity.AsesoriaLegal;

public interface AsesoriaLegalService {
    fusion.fusion.entity.AsesoriaLegal guardarAsesoria(AsesoriaLegal asesoriaLegal);
    List<AsesoriaLegal> obtenerTodasLasAsesorias();
    Optional<AsesoriaLegal> obtenerAsesoriaPorId(Long id);
    void eliminarAsesoria(Long id);
    List<AsesoriaLegal> obtenerAsesoriasPorEstado(String estado);
}