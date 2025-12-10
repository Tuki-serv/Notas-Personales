package com.NotasPersonales.Notas_Personales.Entities.DTOs.UsuarioDTOs;

import java.util.UUID;

public record UsuarioRespuestaDTO(
        UUID id,
        String nombre,
        String email
) {
}
