package com.NotasPersonales.Notas_Personales.Entities;

import jakarta.persistence.Entity;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Usuario extends BaseEntity{
    private String nombre;
    private String email;
    private String password;
}
