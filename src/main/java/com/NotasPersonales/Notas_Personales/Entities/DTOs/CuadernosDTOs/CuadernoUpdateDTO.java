package com.NotasPersonales.Notas_Personales.Entities.DTOs.CuadernosDTOs;

import com.NotasPersonales.Notas_Personales.Entities.DTOs.VersionableDTO;
import jakarta.validation.constraints.Size;

public record CuadernoUpdateDTO(
        @Size(min = 3, max = 20, message = "El nombre debe tener entre 3 y 20 caracteres")
        String nombre,

        @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
        String descripcion,

        Long version
) implements VersionableDTO {
}
