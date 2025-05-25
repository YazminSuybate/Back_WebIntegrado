package fusion.fusion.service.serviceRestante;


import fusion.fusion.entity.entidadesRestantes.AsesoriaLegal;

import java.util.List;
import java.util.Optional;

public interface AsesoriaLegalService {
    fusion.fusion.entity.entidadesRestantes.AsesoriaLegal guardarAsesoria(AsesoriaLegal asesoriaLegal);
    List<AsesoriaLegal> obtenerTodasLasAsesorias();
    Optional<AsesoriaLegal> obtenerAsesoriaPorId(Long id);
    void eliminarAsesoria(Long id);
    List<AsesoriaLegal> obtenerAsesoriasPorClienteId(Long clienteId);
    List<AsesoriaLegal> obtenerAsesoriasPorAbogadoId(Long abogadoId);
    List<AsesoriaLegal> obtenerAsesoriasPorEstado(String estado);
}