package com.NotasPersonales.Notas_Personales.Utils;

import com.NotasPersonales.Notas_Personales.Entities.DTOs.ErrorDTO;
import com.NotasPersonales.Notas_Personales.Entities.Enums.Estado;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;

@RestControllerAdvice
public class GestionadorDeExcepciones {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorDTO> manejarResponseStatus(ResponseStatusException exception){

        ErrorDTO error = new ErrorDTO(exception.getReason(),exception.getStatusCode().value());

        return ResponseEntity.status(exception.getStatusCode()).body(error);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorDTO> manejarErrorDeTipo(MethodArgumentTypeMismatchException exception) {

        String nombreParametro = exception.getName();

        String tipoEsperado = exception.getRequiredType() != null ? exception.getRequiredType().getSimpleName() : "desconocido";

        Object valorRecibido = exception.getValue();

        String mensaje = String.format("El parámetro '%s' recibió un valor inválido ('%s'). Se esperaba un valor de tipo: %s",
                nombreParametro, valorRecibido, tipoEsperado);

        return ResponseEntity.badRequest().body(new ErrorDTO(mensaje, 400));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> manejarValidacion(MethodArgumentNotValidException exception){
        String mensaje = exception.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .findFirst()
                .orElse("Error de validacion");

        return ResponseEntity.badRequest().body(new ErrorDTO(mensaje, 400));
    }

}
