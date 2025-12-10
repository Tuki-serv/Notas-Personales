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
        return null;
    }

    @Override
    public void actulizarEntidad(Cuaderno cuaderno, CuadernoUpdateDTO dto) {

    }
}
