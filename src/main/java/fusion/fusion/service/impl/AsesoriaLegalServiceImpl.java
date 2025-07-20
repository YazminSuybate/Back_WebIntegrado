package fusion.fusion.service.impl;



import fusion.fusion.entity.AsesoriaLegal;
import fusion.fusion.entity.Denuncia;
import fusion.fusion.entity.DenunciaUsuario;
import fusion.fusion.entity.UserEntity;
import fusion.fusion.repository.AsesoriaLegalRepository;
import fusion.fusion.repository.DenunciaRepository;
import fusion.fusion.repository.DenunciaUsuarioRepository;
import fusion.fusion.service.AsesoriaLegalService;

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
    private DenunciaUsuarioRepository denunciaUsuarioRepository;
    @Autowired
    private DenunciaRepository denunciaRepository;

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
    public List<AsesoriaLegal> obtenerAsesoriasPorEstado(String estado) {
        return asesoriaLegalRepository.findByEstado(estado);
    }


    @Override
    public Optional<Denuncia> obtenerDenunciaPorId(Long id) {
        return denunciaRepository.findById(id);
    }
    @Override
    public List<UserEntity> obtenerUsuariosPorDenuncia(Long denunciaId) {
        List<DenunciaUsuario> relaciones = denunciaUsuarioRepository.findByDenunciaId(denunciaId);
        return relaciones.stream().map(DenunciaUsuario::getUsuario).toList();
    }



    @Override
    @Transactional(readOnly = true)
    public List<AsesoriaLegal> obtenerAsesoriasPorDenunciaId(Long denunciaId) {
        return asesoriaLegalRepository.findByDenunciaId(denunciaId);
    }




@Override
    public List<AsesoriaLegal> obtenerAsesoriasPorDenunciaIdYEstado(Long denunciaId, String estado) {
        return asesoriaLegalRepository.findByDenunciaIdAndEstado(denunciaId, estado);
    }

}