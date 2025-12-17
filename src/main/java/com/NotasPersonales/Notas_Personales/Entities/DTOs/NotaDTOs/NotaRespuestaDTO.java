package com.NotasPersonales.Notas_Personales.Entities.DTOs.NotaDTOs;

import java.time.LocalDateTime;
import java.util.UUID;

public record NotaRespuestaDTO(
        UUID id,
        String titulo,
        String contenido,
        UUID cuadernoId,
        Long version,
        LocalDateTime fechaCreacion,
        LocalDateTime fechaUltimaModificacion
) {
}
