package fusion.fusion.service.impl;

import fusion.fusion.entity.SesionPsicologica;
import fusion.fusion.repository.SesionPsicologicaRepository;
import fusion.fusion.service.SesionPsicologicaService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SesionPsicologicaServiceImpl implements SesionPsicologicaService {

    private final SesionPsicologicaRepository sesionPsicologicaRepository;

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
    public List<SesionPsicologica> obtenerSesionesPorEstado(String estado) {
        return sesionPsicologicaRepository.findByEstado(estado);
    }
}