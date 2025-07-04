package fusion.fusion.controller;


import fusion.fusion.entity.DenunciaUsuario;
import fusion.fusion.service.impl.DenunciaUsuarioImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/listausuario")
public class DenunciaUsuarioController {
    private final DenunciaUsuarioImpl service;

    public DenunciaUsuarioController(DenunciaUsuarioImpl service) {
        this.service = service;
    }

    @GetMapping("/usuario/{usuarioId}")
    @PreAuthorize("hasRole('USER')")
    public List<DenunciaUsuario> obtenerPorUsuario(@PathVariable Long usuarioId) {
        return service.ObtenerDenunciaPorUsuario(usuarioId);
    }

}
