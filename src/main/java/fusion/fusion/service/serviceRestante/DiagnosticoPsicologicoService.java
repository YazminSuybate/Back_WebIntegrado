package fusion.fusion.service.serviceRestante;

import fusion.fusion.entity.entidadesRestantes.DiagnosticoPsicologico;

import java.util.List;
import java.util.Optional;

public interface DiagnosticoPsicologicoService {
    DiagnosticoPsicologico guardarDiagnostico(DiagnosticoPsicologico diagnosticoPsicologico);
    List<DiagnosticoPsicologico> obtenerTodosLosDiagnosticos();
    Optional<DiagnosticoPsicologico> obtenerDiagnosticoPorId(Long id);
    void eliminarDiagnostico(Long id);
    List<DiagnosticoPsicologico> obtenerDiagnosticosPorSesionId(Long sesionId);
}