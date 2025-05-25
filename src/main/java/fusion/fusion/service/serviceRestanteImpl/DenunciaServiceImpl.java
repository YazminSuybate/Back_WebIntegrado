package fusion.fusion.service.serviceRestanteImpl;


import fusion.fusion.entity.entidadesRestantes.Denuncia;
import fusion.fusion.repository.repositoryRestante.DenunciaRepository;
import fusion.fusion.service.serviceRestante.DenunciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DenunciaServiceImpl implements DenunciaService {

    private final DenunciaRepository denunciaRepository;

    @Autowired
    public DenunciaServiceImpl(DenunciaRepository denunciaRepository) {
        this.denunciaRepository = denunciaRepository;
    }

    @Override
    public Denuncia guardarDenuncia(Denuncia denuncia) {
        return denunciaRepository.save(denuncia);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Denuncia> obtenerTodasLasDenuncias() {
        return denunciaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Denuncia> obtenerDenunciaPorId(Long id) {
        return denunciaRepository.findById(id);
    }

    @Override
    public void eliminarDenuncia(Long id) {
        denunciaRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Denuncia> obtenerDenunciasPorDenuncianteId(Long denuncianteId) {
        return denunciaRepository.findByDenuncianteId(denuncianteId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Denuncia> obtenerDenunciasPorEstado(String estado) {
        return denunciaRepository.findByEstado(estado);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Denuncia> obtenerDenunciasPorTipo(String tipoDenuncia) {
        return denunciaRepository.findByTipoDenuncia(tipoDenuncia);
    }
}