package com.NotasPersonales.Notas_Personales.Entities.Mappers;

import java.util.function.Consumer;

public interface BaseMapper <E, PostDTO, UpdateDTO, RespuestaDTO>{
    E dtoToEntity(PostDTO dto);
    RespuestaDTO entityToDTO(E e);
    void actualizarEntidad(E e, UpdateDTO dto);

    default void aplicarSiValido(String nuevoValor, String valorActual, Consumer<String> setter){
        if (nuevoValor != null && !nuevoValor.isBlank() && !nuevoValor.equals(valorActual)){
            setter.accept(nuevoValor);
        };
    }
}

