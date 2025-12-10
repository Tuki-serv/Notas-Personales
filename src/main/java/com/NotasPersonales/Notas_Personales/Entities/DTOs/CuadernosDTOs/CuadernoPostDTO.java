package com.NotasPersonales.Notas_Personales.Entities.DTOs.CuadernosDTOs;

import jakarta.validation.constraints.NotBlank;

public record CuadernoPostDTO(
        @NotBlank(message = "El nombre no puede estar vacío")
        String nombre,

        String descripcion,

        @NotBlank(message = "Cada cuaderno debe pertenecer a un usuario")
        Long usuarioID
) {
}
