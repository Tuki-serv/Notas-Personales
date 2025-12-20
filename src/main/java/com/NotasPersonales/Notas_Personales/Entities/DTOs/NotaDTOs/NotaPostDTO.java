package com.NotasPersonales.Notas_Personales.Entities.DTOs.NotaDTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record NotaPostDTO(
        @NotBlank(message = "El titulo no puede estar vacío")
        @Size(min = 3, max = 20, message = "El nombre debe tener entre 3 y 20 caracteres")
        String titulo,

        @NotNull(message = "Cada nota debe pertenecer a un cuaderno")
        UUID cuadernoID
) {
}
