package com.NotasPersonales.Notas_Personales.Entities.DTOs.NotaDTOs;

import com.NotasPersonales.Notas_Personales.Entities.DTOs.VersionableDTO;

public record NotaUpdateDTO(
        String titulo,
        String contenido,
        Long version
) implements VersionableDTO {
}
