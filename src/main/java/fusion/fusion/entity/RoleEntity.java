package fusion.fusion.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * NUEVA CLASE: Entidad para representar los roles en el sistema
 * Esta clase se mapea a la tabla 'tbl_roles' en la base de datos
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_roles")
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;
}