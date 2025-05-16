package com.grupo6.back_webintegrado.model.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "asesorias_legales")
public class AsesoriaLegal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "id_defensor")
    private Usuario empleado;

    @ManyToOne
    @JoinColumn(name = "id_victima")
    private Usuario usuario;

    @Column(name = "fecha")
    private LocalDateTime fecha;

    @Column(name = "tema", length = 50)
    private String tema;

    @Column(name = "resumen")
    private String resumen;
}
