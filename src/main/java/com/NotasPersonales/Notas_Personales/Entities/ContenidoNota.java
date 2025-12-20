package com.NotasPersonales.Notas_Personales.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;
import org.hibernate.annotations.SQLDelete;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@SQLDelete(sql = "UPDATE contenido_nota SET eliminado = true WHERE id = ? AND version = ?")
public class ContenidoNota extends BaseEntity{
    @Column(columnDefinition = "TEXT")
    private String texto;
}
