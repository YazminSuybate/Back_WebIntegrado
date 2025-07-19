package fusion.fusion.controller;


import fusion.fusion.entity.Denuncia;
import fusion.fusion.entity.DenunciaUsuario;
import fusion.fusion.entity.UserEntity;
import fusion.fusion.repository.UserRepository;
import fusion.fusion.service.DenunciaUsuarioService;
import fusion.fusion.service.impl.DenunciaUsuarioImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/denunciasusuario")
public class DenunciaUsuarioController {

    private final DenunciaUsuarioService service;
    private final UserRepository userRepository;

    public DenunciaUsuarioController(DenunciaUsuarioService service, UserRepository userRepository) {
        this.service = service;
        this.userRepository = userRepository;
    }

    // ✅ Este endpoint devuelve las denuncias del usuario autenticado
    @GetMapping("/misdenuncias")
    public List<Denuncia> obtenerMisDenuncias(@AuthenticationPrincipal UserDetails userDetails) {
        String email = userDetails.getUsername();

        UserEntity usuario = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return service.obtenerDenunciasDelUsuario(usuario.getId());
    }









}
