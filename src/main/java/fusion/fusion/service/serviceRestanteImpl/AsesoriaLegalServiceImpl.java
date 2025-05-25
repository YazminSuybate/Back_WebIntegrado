package fusion.fusion.service.serviceRestanteImpl;



import fusion.fusion.entity.entidadesRestantes.AsesoriaLegal;
import fusion.fusion.repository.repositoryRestante.AsesoriaLegalRepository;

import fusion.fusion.service.serviceRestante.AsesoriaLegalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AsesoriaLegalServiceImpl implements AsesoriaLegalService {

    private final AsesoriaLegalRepository asesoriaLegalRepository;

    @Autowired
    public AsesoriaLegalServiceImpl(AsesoriaLegalRepository asesoriaLegalRepository) {
        this.asesoriaLegalRepository = asesoriaLegalRepository;
    }

    @Override
    public AsesoriaLegal guardarAsesoria(AsesoriaLegal asesoriaLegal) {
        return asesoriaLegalRepository.save(asesoriaLegal);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AsesoriaLegal> obtenerTodasLasAsesorias() {
        return asesoriaLegalRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AsesoriaLegal> obtenerAsesoriaPorId(Long id) {
        return asesoriaLegalRepository.findById(id);
    }

    @Override
    public void eliminarAsesoria(Long id) {
        asesoriaLegalRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AsesoriaLegal> obtenerAsesoriasPorClienteId(Long clienteId) {
        return asesoriaLegalRepository.findByClienteId(clienteId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AsesoriaLegal> obtenerAsesoriasPorAbogadoId(Long abogadoId) {
        return asesoriaLegalRepository.findByAbogadoId(abogadoId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AsesoriaLegal> obtenerAsesoriasPorEstado(String estado) {
        return asesoriaLegalRepository.findByEstado(estado);
    }
}