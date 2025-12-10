package com.NotasPersonales.Notas_Personales.Entities.DTOs.CuadernosDTOs;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record CuadernoPostDTO(
        @NotBlank(message = "El nombre no puede estar vacío")
        String nombre,

        String descripcion,

        @NotBlank(message = "Cada cuaderno debe pertenecer a un usuario")
        UUID usuarioID
) {
}
