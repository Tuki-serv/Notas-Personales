package com.NotasPersonales.Notas_Personales.Entities.Mappers;

import com.NotasPersonales.Notas_Personales.Entities.Cuaderno;
import com.NotasPersonales.Notas_Personales.Entities.DTOs.CuadernosDTOs.CuadernoPostDTO;
import com.NotasPersonales.Notas_Personales.Entities.DTOs.CuadernosDTOs.CuadernoRespuestaDTO;
import com.NotasPersonales.Notas_Personales.Entities.DTOs.CuadernosDTOs.CuadernoUpdateDTO;

public class CuadernoMapper implements BaseMapper <Cuaderno, CuadernoPostDTO, CuadernoUpdateDTO, CuadernoRespuestaDTO>{
    @Override
    public Cuaderno dtoToEntity(CuadernoPostDTO dto) {
        return Cuaderno.builder()
                .nombre(dto.nombre())
                .descripcion(dto.descripcion())
                .build();
    }

    @Override
    public CuadernoRespuestaDTO entityToDTO(Cuaderno cuaderno) {
        return new CuadernoRespuestaDTO(cuaderno.getPublicId(),cuaderno.getNombre(), cuaderno.getDescripcion());
    }

    @Override
    public void actulizarEntidad(Cuaderno cuaderno, CuadernoUpdateDTO dto) {
        if(dto.nombre() != null && !dto.nombre().isBlank() && !dto.nombre().equals(cuaderno.getNombre())){
            cuaderno.setNombre(dto.nombre());
        }

        if(dto.descripcion() != null && !dto.descripcion().isBlank() && !dto.descripcion().equals(cuaderno.getDescripcion())){
            cuaderno.setDescripcion(dto.descripcion());
        }
    }
}
