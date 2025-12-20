package com.NotasPersonales.Notas_Personales.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Lob;
import jakarta.persistence.Column;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@SQLDelete(sql = "UPDATE nota SET eliminado = true WHERE id = ? AND version = ?")
//@SQLRestriction("eliminado = false")
public class Nota extends BaseEntity{
    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String contenido;

    @ManyToOne
    @JoinColumn(name = "cuaderno_id", nullable = false)
    private Cuaderno cuaderno;
}
