package com.NotasPersonales.Notas_Personales.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Lob;
import jakarta.persistence.Column;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Nota extends BaseEntity{
    private String titulo;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String contenido;

    @ManyToOne
    @JoinColumn(name = "cuaderno_id", nullable = false)
    private Cuaderno cuaderno;
}
