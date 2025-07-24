package fusion.fusion.service.impl;

import fusion.fusion.entity.AsesoriaLegal;
import fusion.fusion.entity.SeguimientoAsesoria;
import fusion.fusion.repository.AsesoriaLegalRepository;
import fusion.fusion.repository.SeguimientoAsesoriaRepository;
import fusion.fusion.service.SeguimientoAsesoriaService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SeguimientoAsesoriaServiceImpl implements SeguimientoAsesoriaService {

    private final SeguimientoAsesoriaRepository seguimientoAsesoriaRepository;

    private final AsesoriaLegalRepository asesoriaLegalRepository;

    public SeguimientoAsesoriaServiceImpl(SeguimientoAsesoriaRepository seguimientoAsesoriaRepository,
            AsesoriaLegalRepository asesoriaLegalRepository) {
        this.seguimientoAsesoriaRepository = seguimientoAsesoriaRepository;
        this.asesoriaLegalRepository = asesoriaLegalRepository;
    }

    @Override
    public SeguimientoAsesoria guardarSeguimiento(SeguimientoAsesoria seguimientoAsesoria) {
        return seguimientoAsesoriaRepository.save(seguimientoAsesoria);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SeguimientoAsesoria> obtenerTodosLosSeguimientos() {
        return seguimientoAsesoriaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<SeguimientoAsesoria> obtenerSeguimientoPorId(Long id) {
        return seguimientoAsesoriaRepository.findById(id);
    }

    @Override
    public void eliminarSeguimiento(Long id) {
        seguimientoAsesoriaRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SeguimientoAsesoria> obtenerSeguimientosPorAsesoriaId(Long asesoriaId) {
        return seguimientoAsesoriaRepository.findByAsesoriaId(asesoriaId);
    }

    @Override
    public List<SeguimientoAsesoria> obtenerSeguimientosPorDenunciaId(Long denunciaId) {
        List<AsesoriaLegal> asesorias = asesoriaLegalRepository.findByDenunciaId(denunciaId);
        List<SeguimientoAsesoria> resultados = new ArrayList<>();

        for (AsesoriaLegal asesoria : asesorias) {
            resultados.addAll(seguimientoAsesoriaRepository.findByAsesoriaId(asesoria.getId()));
        }

        return resultados;
    }
}