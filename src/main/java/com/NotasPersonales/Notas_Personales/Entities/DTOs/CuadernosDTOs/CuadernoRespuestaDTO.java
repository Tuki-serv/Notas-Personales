package com.NotasPersonales.Notas_Personales.Entities.DTOs.CuadernosDTOs;

import java.util.UUID;

public record CuadernoRespuestaDTO(
        UUID id,
        String nombre,
        String descripcion
) {
}
