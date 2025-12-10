package com.NotasPersonales.Notas_Personales.Entities;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Usuario extends BaseEntity{
    private String nombre;
    private String email;
    private String password;
}
