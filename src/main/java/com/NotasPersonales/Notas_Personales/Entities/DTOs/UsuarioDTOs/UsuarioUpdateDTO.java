package com.NotasPersonales.Notas_Personales.Entities.DTOs.UsuarioDTOs;

import com.NotasPersonales.Notas_Personales.Entities.DTOs.VersionableDTO;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UsuarioUpdateDTO(
        @Size(min = 3, max = 20, message = "El nombre debe tener entre 3 y 20 caracteres")
        String nombre,

        @Email(message = "Formato de email inválido")
        String email,

        @NotBlank(message = "La contraseña no puede estar vacía")
        @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,20}$",
                message = "La contraseña debe tener entre 8 y 20 caracteres, contener al menos un número, una minúscula y una mayúscula.")
        String password,

        Long version
) implements VersionableDTO {
}
