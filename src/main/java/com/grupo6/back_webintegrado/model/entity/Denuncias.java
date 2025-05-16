package com.grupo6.back_webintegrado.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "denuncias")
public class Denuncias {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "id_victima", nullable = false)
    private Usuario usuario;

    @Column(name = "fecha", nullable = false)
    private LocalDateTime fecha;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "estado", length = 50, nullable = false)
    private String estado;

    @OneToOne(mappedBy = "denuncia")
    private Archivos archivo;

    @OneToOne(mappedBy = "denuncia")
    private Seguimientos seguimiento;
}
