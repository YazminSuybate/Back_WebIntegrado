package com.grupo6.back_webintegrado.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sesiones_psicologicas")
public class SesionesPsico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "id_psicologo")
    private Usuario empleado;

    @ManyToOne
    @JoinColumn(name = "id_victima")
    private Usuario usuario;

    @Column(name = "fecha")
    private LocalDateTime fecha;

    @Column(name = "duracion")
    private int duracion;

    @Column(name = "observaciones")
    private String observaciones;
}
