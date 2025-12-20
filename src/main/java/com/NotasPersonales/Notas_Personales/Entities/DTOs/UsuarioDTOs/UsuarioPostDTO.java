package com.NotasPersonales.Notas_Personales.Entities.DTOs.UsuarioDTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UsuarioPostDTO(
        @NotBlank(message = "El nombre no puede estar vacío")
        @Size(min = 3, max = 15, message = "El nombre debe tener entre 3 y 15 caracteres")
        String nombre,

        @NotBlank(message = "El email no puede estar vacío")
        @Email(message = "Formato de email inválido")
        String email,

        @NotBlank(message = "La contraseña no puede estar vacía")
        @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,20}$",
                message = "La contraseña debe tener entre 8 y 20 caracteres, contener al menos un número, una minúscula y una mayúscula.")
        String password
) {
}
