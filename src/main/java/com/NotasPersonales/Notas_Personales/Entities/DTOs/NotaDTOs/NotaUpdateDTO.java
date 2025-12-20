package com.NotasPersonales.Notas_Personales.Entities.DTOs.NotaDTOs;

import com.NotasPersonales.Notas_Personales.Entities.DTOs.VersionableDTO;
import jakarta.validation.constraints.Size;

public record NotaUpdateDTO(
        @Size(min = 3, max = 20, message = "El nombre debe tener entre 3 y 20 caracteres")
        String titulo,
        @Size(min = 1, message = "El nombre debe por lo menos tener 1 caracter")
        String contenido,
        Long version
) implements VersionableDTO {
}
