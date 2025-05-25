package fusion.fusion.service.serviceRestanteImpl;

import fusion.fusion.entity.entidadesRestantes.SesionPsicologica;
import fusion.fusion.repository.repositoryRestante.SesionPsicologicaRepository;
import fusion.fusion.service.serviceRestante.SesionPsicologicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SesionPsicologicaServiceImpl implements SesionPsicologicaService {

    private final SesionPsicologicaRepository sesionPsicologicaRepository;

    @Autowired
    public SesionPsicologicaServiceImpl(SesionPsicologicaRepository sesionPsicologicaRepository) {
        this.sesionPsicologicaRepository = sesionPsicologicaRepository;
    }

    @Override
    public SesionPsicologica guardarSesion(SesionPsicologica sesionPsicologica) {
        return sesionPsicologicaRepository.save(sesionPsicologica);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SesionPsicologica> obtenerTodasLasSesiones() {
        return sesionPsicologicaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<SesionPsicologica> obtenerSesionPorId(Long id) {
        return sesionPsicologicaRepository.findById(id);
    }

    @Override
    public void eliminarSesion(Long id) {
        sesionPsicologicaRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SesionPsicologica> obtenerSesionesPorPacienteId(Long pacienteId) {
        return sesionPsicologicaRepository.findByPacienteId(pacienteId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SesionPsicologica> obtenerSesionesPorPsicologoId(Long psicologoId) {
        return sesionPsicologicaRepository.findByPsicologoId(psicologoId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SesionPsicologica> obtenerSesionesPorEstado(String estado) {
        return sesionPsicologicaRepository.findByEstado(estado);
    }
}