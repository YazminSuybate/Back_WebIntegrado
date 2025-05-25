package fusion.fusion.io;

import jakarta.validation.constraints.*;
import lombok.*;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileRequest {
    @NotBlank(message = "el nombre no puede estar en blanco")
    private String nombre;
    @Email(message = "Tiene que ser un email valido")
    @NotNull(message = "El email no puede ser vacio")
    private String email;
    @Size(min = 6, message = "La contraseña tine que ser de minimo 6 digitos")
    private String password;


}
