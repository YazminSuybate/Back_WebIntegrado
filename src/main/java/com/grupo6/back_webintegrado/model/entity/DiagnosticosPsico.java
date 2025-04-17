package com.grupo6.back_webintegrado.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "diagnosticos_psicologicos")
public class DiagnosticosPsico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "id_sesion")
    private SesionesPsico sesion;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "recomendaciones")
    private String recomendaciones;
}
