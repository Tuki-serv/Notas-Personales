package com.NotasPersonales.Notas_Personales.Entities.DTOs.CuadernosDTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record CuadernoPostDTO(
        @NotBlank(message = "El nombre no puede estar vacío")
        @Size(min = 3, max = 20, message = "El nombre debe tener entre 3 y 20 caracteres")
        String nombre,

        @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
        String descripcion,

        @NotNull(message = "Cada cuaderno debe pertenecer a un usuario")
        UUID usuarioID
) {
}
