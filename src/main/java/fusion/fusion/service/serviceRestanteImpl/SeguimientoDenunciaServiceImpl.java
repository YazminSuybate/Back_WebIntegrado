package fusion.fusion.service.serviceRestanteImpl;

import fusion.fusion.entity.entidadesRestantes.SeguimientoDenuncia;
import fusion.fusion.repository.repositoryRestante.SeguimientoDenunciaRepository;
import fusion.fusion.service.serviceRestante.SeguimientoDenunciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SeguimientoDenunciaServiceImpl implements SeguimientoDenunciaService {

    private final SeguimientoDenunciaRepository seguimientoDenunciaRepository;

    @Autowired
    public SeguimientoDenunciaServiceImpl(SeguimientoDenunciaRepository seguimientoDenunciaRepository) {
        this.seguimientoDenunciaRepository = seguimientoDenunciaRepository;
    }

    @Override
    public SeguimientoDenuncia guardarSeguimiento(SeguimientoDenuncia seguimientoDenuncia) {
        return seguimientoDenunciaRepository.save(seguimientoDenuncia);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SeguimientoDenuncia> obtenerTodosLosSeguimientos() {
        return seguimientoDenunciaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<SeguimientoDenuncia> obtenerSeguimientoPorId(Long id) {
        return seguimientoDenunciaRepository.findById(id);
    }

    @Override
    public void eliminarSeguimiento(Long id) {
        seguimientoDenunciaRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SeguimientoDenuncia> obtenerSeguimientosPorDenunciaId(Long denunciaId) {
        return seguimientoDenunciaRepository.findByDenunciaId(denunciaId);
    }
}