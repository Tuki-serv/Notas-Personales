package com.NotasPersonales.Notas_Personales.Entities.DTOs.NotaDTOs;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record NotaPostDTO(
        @NotBlank(message = "El titulo no puede estar vacío")
        String titulo,

        String contenido,

        @NotBlank(message = "Cada nota debe pertenecer a un cuaderno")
        UUID cuadernoID
) {
}
