package com.NotasPersonales.Notas_Personales.Entities.DTOs.CuadernosDTOs;

import java.time.LocalDateTime;
import java.util.UUID;

public record CuadernoRespuestaDTO(
        UUID id,
        String nombre,
        String descripcion,
        UUID usuarioId,
        LocalDateTime fechaCreacion,
        LocalDateTime fechaUltimaModificacion
) {
}
