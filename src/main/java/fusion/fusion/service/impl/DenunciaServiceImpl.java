package fusion.fusion.service.impl;


import fusion.fusion.entity.Denuncia;
import fusion.fusion.repository.DenunciaRepository;
import fusion.fusion.service.DenunciaService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DenunciaServiceImpl implements DenunciaService {

    private final DenunciaRepository denunciaRepository;

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
    public List<Denuncia> obtenerDenunciasPorEstado(String estado) {
        return denunciaRepository.findByEstado(estado);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Denuncia> obtenerDenunciasPorTipo(String tipoDenuncia) {
        return denunciaRepository.findByTipoDenuncia(tipoDenuncia);
    }
}