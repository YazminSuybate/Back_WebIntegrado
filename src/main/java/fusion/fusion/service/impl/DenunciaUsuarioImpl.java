package fusion.fusion.service.impl;

import fusion.fusion.entity.Denuncia;
import fusion.fusion.entity.DenunciaUsuario;
import fusion.fusion.repository.DenunciaUsuarioRepository;
import fusion.fusion.service.DenunciaUsuarioService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DenunciaUsuarioImpl implements DenunciaUsuarioService {

    private final DenunciaUsuarioRepository denunciaUsuarioRepository;

    public DenunciaUsuarioImpl(DenunciaUsuarioRepository denunciaUsuarioRepository) {
        this.denunciaUsuarioRepository = denunciaUsuarioRepository;
    }

    @Override
    public List<DenunciaUsuario> ObtenerDenunciaPorUsuario(Long usuarioId) {
        return denunciaUsuarioRepository.findByUsuarioId(usuarioId);
    }

    @Override
    public DenunciaUsuario CrearDenunciausuario(DenunciaUsuario denunciaUsuario) {
        return denunciaUsuarioRepository.save(denunciaUsuario);
    }

    @Override
    public boolean existeRelacion(Long denunciaId, Long usuarioId) {
        return denunciaUsuarioRepository.existsByIdDenunciaIdAndIdUsuarioId(denunciaId, usuarioId);
    }
    @Override
    public List<DenunciaUsuario> ObtenerDenunciaPorDenunciaId(Long denunciaId) {
        return denunciaUsuarioRepository.findByDenunciaId(denunciaId);
    }

    @Override
    public List<DenunciaUsuario> obtenerUsuariosPorDenuncia(Long denunciaId) {
        return denunciaUsuarioRepository.findByDenunciaId(denunciaId);
    }

    @Override
    public List<Denuncia> obtenerDenunciasDelUsuario(Long usuarioId) {
        return denunciaUsuarioRepository.findDenunciasByUsuarioId(usuarioId);
    }


}
