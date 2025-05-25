package fusion.fusion.service;

import java.util.List;
import java.util.Optional;

import fusion.fusion.entity.DiagnosticoPsicologico;

public interface DiagnosticoPsicologicoService {
    DiagnosticoPsicologico guardarDiagnostico(DiagnosticoPsicologico diagnosticoPsicologico);
    List<DiagnosticoPsicologico> obtenerTodosLosDiagnosticos();
    Optional<DiagnosticoPsicologico> obtenerDiagnosticoPorId(Long id);
    void eliminarDiagnostico(Long id);
    List<DiagnosticoPsicologico> obtenerDiagnosticosPorSesionId(Long sesionId);
}