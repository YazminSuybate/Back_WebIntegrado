package com.grupo6.back_webintegrado.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table (name = "usuario")
@Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nombre", length = 50, nullable = false)
    private String nombre;

    @Column(name = "apellido", length = 50, nullable = false)
    private String apellido;

    @Column(name = "correo", nullable = false)
    private String correo;

    @Column(name = "password", nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "usuarios_roles",
            joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id", referencedColumnName = "id"))
    private Collection<Rol> roles;

}
