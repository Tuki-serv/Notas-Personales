package com.NotasPersonales.Notas_Personales.Entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.util.ArrayList;
import java.util.List;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@SQLDelete(sql = "UPDATE cuaderno SET eliminado = true WHERE id = ?")
@SQLRestriction("eliminado = false")
public class Cuaderno extends BaseEntity{
    private String nombre;
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @OneToMany(mappedBy = "cuaderno", cascade = CascadeType.REMOVE)
    @Builder.Default
    private List<Nota> notas = new ArrayList<>();
}
