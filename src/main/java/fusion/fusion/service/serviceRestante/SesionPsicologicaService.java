package fusion.fusion.service.serviceRestante;

import fusion.fusion.entity.entidadesRestantes.SesionPsicologica;

import java.util.List;
import java.util.Optional;

public interface SesionPsicologicaService {
    SesionPsicologica guardarSesion(SesionPsicologica sesionPsicologica);
    List<SesionPsicologica> obtenerTodasLasSesiones();
    Optional<SesionPsicologica> obtenerSesionPorId(Long id);
    void eliminarSesion(Long id);
    List<SesionPsicologica> obtenerSesionesPorPacienteId(Long pacienteId);
    List<SesionPsicologica> obtenerSesionesPorPsicologoId(Long psicologoId);
    List<SesionPsicologica> obtenerSesionesPorEstado(String estado);
}