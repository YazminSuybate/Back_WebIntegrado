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
@Table(name = "archivos")
public class Archivos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nombre_archivo", length = 50)
    private String nombre_archi;

    @Column(name = "ruta")
    private String ruta;

    @Column(name = "tipo", length = 50)
    private String tipo;

    @Column(name = "fecha_subida")
    private LocalDateTime fecha;

    @OneToOne
    @JoinColumn(name = "id_denuncia")
    private Denuncias denuncia;
}
