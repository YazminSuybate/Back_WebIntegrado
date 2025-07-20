package fusion.fusion.service;


import java.util.List;
import java.util.Optional;

import fusion.fusion.entity.AsesoriaLegal;
import fusion.fusion.entity.Denuncia;
import fusion.fusion.entity.UserEntity;

public interface AsesoriaLegalService {
    fusion.fusion.entity.AsesoriaLegal guardarAsesoria(AsesoriaLegal asesoriaLegal);
    List<AsesoriaLegal> obtenerTodasLasAsesorias();
    Optional<AsesoriaLegal> obtenerAsesoriaPorId(Long id);
    void eliminarAsesoria(Long id);
    List<AsesoriaLegal> obtenerAsesoriasPorEstado(String estado);
    Optional<Denuncia> obtenerDenunciaPorId(Long id);
    List<UserEntity> obtenerUsuariosPorDenuncia(Long denunciaId);
    List<AsesoriaLegal> obtenerAsesoriasPorDenunciaId(Long denunciaId);
    public List<AsesoriaLegal> obtenerAsesoriasPorDenunciaIdYEstado(Long denunciaId, String estado);
}