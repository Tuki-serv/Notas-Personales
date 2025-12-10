package com.NotasPersonales.Notas_Personales.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Nota extends BaseEntity{
    private String titulo;
    private String contenido;

    @ManyToOne
    @JoinColumn(name = "cuaderno_id", nullable = false)
    private Cuaderno cuaderno;
}
