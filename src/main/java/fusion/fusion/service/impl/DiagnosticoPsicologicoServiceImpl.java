package fusion.fusion.service.impl;

import fusion.fusion.entity.DiagnosticoPsicologico;
import fusion.fusion.repository.DiagnosticoPsicologicoRepository;
import fusion.fusion.service.DiagnosticoPsicologicoService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DiagnosticoPsicologicoServiceImpl implements DiagnosticoPsicologicoService {

    private final DiagnosticoPsicologicoRepository diagnosticoPsicologicoRepository;

    public DiagnosticoPsicologicoServiceImpl(DiagnosticoPsicologicoRepository diagnosticoPsicologicoRepository) {
        this.diagnosticoPsicologicoRepository = diagnosticoPsicologicoRepository;
    }

    @Override
    public DiagnosticoPsicologico guardarDiagnostico(DiagnosticoPsicologico diagnosticoPsicologico) {
        return diagnosticoPsicologicoRepository.save(diagnosticoPsicologico);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DiagnosticoPsicologico> obtenerTodosLosDiagnosticos() {
        return diagnosticoPsicologicoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DiagnosticoPsicologico> obtenerDiagnosticoPorId(Long id) {
        return diagnosticoPsicologicoRepository.findById(id);
    }

    @Override
    public void eliminarDiagnostico(Long id) {
        diagnosticoPsicologicoRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DiagnosticoPsicologico> obtenerDiagnosticosPorSesionId(Long sesionId) {
        return diagnosticoPsicologicoRepository.findBySesionId(sesionId);
    }
}