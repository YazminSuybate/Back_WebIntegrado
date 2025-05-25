package fusion.fusion.service.serviceRestante;


import fusion.fusion.entity.entidadesRestantes.Denuncia;

import java.util.List;
import java.util.Optional;

public interface DenunciaService {
    Denuncia guardarDenuncia(Denuncia denuncia);
    List<Denuncia> obtenerTodasLasDenuncias();
    Optional<Denuncia> obtenerDenunciaPorId(Long id);
    void eliminarDenuncia(Long id);
    List<Denuncia> obtenerDenunciasPorDenuncianteId(Long denuncianteId);
    List<Denuncia> obtenerDenunciasPorEstado(String estado);
    List<Denuncia> obtenerDenunciasPorTipo(String tipoDenuncia);
}