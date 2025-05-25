package fusion.fusion.service.serviceRestanteImpl;


import fusion.fusion.entity.entidadesRestantes.Archivo;
import fusion.fusion.repository.repositoryRestante.ArchivoRepository;

import fusion.fusion.service.serviceRestante.ArchivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ArchivoServiceImpl implements ArchivoService {

    private final ArchivoRepository archivoRepository;

    @Autowired
    public ArchivoServiceImpl(ArchivoRepository archivoRepository) {
        this.archivoRepository = archivoRepository;
    }

    @Override
    public Archivo guardarArchivo(Archivo archivo) {
        return archivoRepository.save(archivo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Archivo> obtenerTodosLosArchivos() {
        return archivoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Archivo> obtenerArchivoPorId(Long id) {
        return archivoRepository.findById(id);
    }

    @Override
    public void eliminarArchivo(Long id) {
        archivoRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Archivo> obtenerArchivosPorDenunciaId(Long denunciaId) {
        return archivoRepository.findByDenunciaId(denunciaId);
    }
}