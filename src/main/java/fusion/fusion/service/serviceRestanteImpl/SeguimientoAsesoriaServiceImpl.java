package fusion.fusion.service.serviceRestanteImpl;


import fusion.fusion.entity.entidadesRestantes.SeguimientoAsesoria;
import fusion.fusion.repository.repositoryRestante.SeguimientoAsesoriaRepository;
import fusion.fusion.service.serviceRestante.SeguimientoAsesoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SeguimientoAsesoriaServiceImpl implements SeguimientoAsesoriaService {

    private final SeguimientoAsesoriaRepository seguimientoAsesoriaRepository;

    @Autowired
    public SeguimientoAsesoriaServiceImpl(SeguimientoAsesoriaRepository seguimientoAsesoriaRepository) {
        this.seguimientoAsesoriaRepository = seguimientoAsesoriaRepository;
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
}