package com.NotasPersonales.Notas_Personales.Entities.Mappers;

public interface BaseMapper <E, PostDTO, UpdateDTO, RespuestaDTO>{
    E dtoToEntity(PostDTO dto);
    RespuestaDTO entityToDTO(E e);
    void actualizarEntidad(E e, UpdateDTO dto);
}

