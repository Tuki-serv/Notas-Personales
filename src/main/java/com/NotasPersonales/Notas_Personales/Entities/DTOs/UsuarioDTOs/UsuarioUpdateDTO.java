package com.NotasPersonales.Notas_Personales.Entities.DTOs.UsuarioDTOs;

import jakarta.validation.constraints.Email;

public record UsuarioUpdateDTO(
        String nombre,

        @Email(message = "Formato de email inválido")
        String email,

        String password
) {
}
