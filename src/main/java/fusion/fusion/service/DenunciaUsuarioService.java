package fusion.fusion.service;


import fusion.fusion.entity.DenunciaUsuario;

import java.util.List;

public interface DenunciaUsuarioService {
    List<DenunciaUsuario> ObtenerDenunciaPorUsuario(Long usuarioId);
    DenunciaUsuario CrearDenunciausuario(DenunciaUsuario denunciaUsuario);

}
