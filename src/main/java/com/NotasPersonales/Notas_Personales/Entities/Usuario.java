package com.NotasPersonales.Notas_Personales.Entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
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
@SQLDelete(sql = "UPDATE usuario SET eliminado = true WHERE id = ? AND version = ?")
//@SQLRestriction("eliminado = false")
public class Usuario extends BaseEntity{
    private String nombre;
    private String email;
    private String password;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.REMOVE)
    @Builder.Default
    private List<Cuaderno> cuadernos = new ArrayList<>();
}
