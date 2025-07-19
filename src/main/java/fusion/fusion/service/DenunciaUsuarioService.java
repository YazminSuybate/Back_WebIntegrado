package fusion.fusion.service;


import fusion.fusion.entity.Denuncia;
import fusion.fusion.entity.DenunciaUsuario;

import java.util.List;

public interface DenunciaUsuarioService {
    List<DenunciaUsuario> ObtenerDenunciaPorUsuario(Long usuarioId);
    DenunciaUsuario CrearDenunciausuario(DenunciaUsuario denunciaUsuario);
    boolean existeRelacion(Long denunciaId, Long usuarioId);

    //Optiene las DenunciaUsuario relacionadas al id de la denuncia
    List<DenunciaUsuario> ObtenerDenunciaPorDenunciaId(Long denunciaId);


    List<DenunciaUsuario> obtenerUsuariosPorDenuncia(Long denunciaId);


    List<Denuncia> obtenerDenunciasDelUsuario(Long usuarioId);


}
