package com.NotasPersonales.Notas_Personales.Utils;

import com.NotasPersonales.Notas_Personales.Entities.DTOs.ErrorDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class GestionadorDeExcepciones {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorDTO> manejarResponseStatus(ResponseStatusException exception){

        ErrorDTO error = new ErrorDTO(exception.getReason(),exception.getStatusCode().value());

        return ResponseEntity.status(exception.getStatusCode()).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> mejarValidacion(MethodArgumentNotValidException exception){
        String mensaje = exception.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .findFirst()
                .orElse("Error de validacion");

        return ResponseEntity.badRequest().body(new ErrorDTO(mensaje, 400));
    }

}
