package fusion.fusion.service;

import java.util.List;
import java.util.Optional;

import fusion.fusion.entity.SesionPsicologica;

public interface SesionPsicologicaService {
    SesionPsicologica guardarSesion(SesionPsicologica sesionPsicologica);
    List<SesionPsicologica> obtenerTodasLasSesiones();
    Optional<SesionPsicologica> obtenerSesionPorId(Long id);
    void eliminarSesion(Long id);
    List<SesionPsicologica> obtenerSesionesPorEstado(String estado);
}