package fusion.fusion.service;


import java.util.List;
import java.util.Optional;

import fusion.fusion.entity.Denuncia;

public interface DenunciaService {
    Denuncia guardarDenuncia(Denuncia denuncia);
    List<Denuncia> obtenerTodasLasDenuncias();
    Optional<Denuncia> obtenerDenunciaPorId(Long id);
    void eliminarDenuncia(Long id);
    List<Denuncia> obtenerDenunciasPorEstado(String estado);
    List<Denuncia> obtenerDenunciasPorTipo(String tipoDenuncia);
}