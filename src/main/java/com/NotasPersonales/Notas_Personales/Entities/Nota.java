package com.NotasPersonales.Notas_Personales.Entities;

import jakarta.persistence.*;
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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contenido_id")
    @Builder.Default
    private ContenidoNota contenido = new ContenidoNota("");

    @ManyToOne
    @JoinColumn(name = "cuaderno_id", nullable = false)
    private Cuaderno cuaderno;
}
