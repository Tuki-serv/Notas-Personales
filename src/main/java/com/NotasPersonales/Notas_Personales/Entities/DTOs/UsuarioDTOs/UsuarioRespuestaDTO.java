package com.NotasPersonales.Notas_Personales.Entities.DTOs.UsuarioDTOs;

import java.time.LocalDateTime;
import java.util.UUID;

public record UsuarioRespuestaDTO(
        UUID id,
        String nombre,
        String email,
        Long version,
        LocalDateTime fechaCreacion,
        LocalDateTime fechaUltimaModificacion
) {
}
