package com.grupo6.back_webintegrado.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "seguimientos")
public class Seguimientos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "id_denuncias")
    private Denuncias denuncia;

    @Column(name = "fecha")
    private LocalDateTime fecha;

    @Column(name = "comentario")
    private String comentario;
}
