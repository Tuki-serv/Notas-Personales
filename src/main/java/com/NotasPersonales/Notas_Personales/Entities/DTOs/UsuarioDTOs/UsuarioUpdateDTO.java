package com.NotasPersonales.Notas_Personales.Entities.DTOs.UsuarioDTOs;

import com.NotasPersonales.Notas_Personales.Entities.DTOs.VersionableDTO;
import jakarta.validation.constraints.Email;

public record UsuarioUpdateDTO(
        String nombre,

        @Email(message = "Formato de email inválido")
        String email,

        String password,

        Long version
) implements VersionableDTO {
}
