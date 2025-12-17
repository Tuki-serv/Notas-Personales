package com.NotasPersonales.Notas_Personales.Entities.DTOs.CuadernosDTOs;

import com.NotasPersonales.Notas_Personales.Entities.DTOs.VersionableDTO;

public record CuadernoUpdateDTO(
        String nombre,
        String descripcion,
        Long version
) implements VersionableDTO {
}
