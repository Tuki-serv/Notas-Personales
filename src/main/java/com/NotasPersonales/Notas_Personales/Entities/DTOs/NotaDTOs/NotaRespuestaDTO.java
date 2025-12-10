package com.NotasPersonales.Notas_Personales.Entities.DTOs.NotaDTOs;

import java.util.UUID;

public record NotaRespuestaDTO(
        UUID id,
        String titulo,
        String contenido
) {
}
