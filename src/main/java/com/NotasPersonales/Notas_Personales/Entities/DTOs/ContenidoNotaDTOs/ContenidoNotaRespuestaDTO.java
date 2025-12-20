package com.NotasPersonales.Notas_Personales.Entities.DTOs.ContenidoNotaDTOs;

import java.time.LocalDateTime;
import java.util.UUID;

public record ContenidoNotaRespuestaDTO(
        UUID id,
        String titulo,
        String contenido,
        UUID cuadernoId,
        Long version,
        LocalDateTime fechaCreacion,
        LocalDateTime fechaUltimaModificacion
) {
}
