package com.NotasPersonales.Notas_Personales.Entities.Mappers;

import com.NotasPersonales.Notas_Personales.Entities.Cuaderno;
import com.NotasPersonales.Notas_Personales.Entities.DTOs.CuadernosDTOs.CuadernoPostDTO;
import com.NotasPersonales.Notas_Personales.Entities.DTOs.CuadernosDTOs.CuadernoRespuestaDTO;
import com.NotasPersonales.Notas_Personales.Entities.DTOs.CuadernosDTOs.CuadernoUpdateDTO;
import org.springframework.stereotype.Component;

@Component
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
        return new CuadernoRespuestaDTO(cuaderno.getPublicId(),cuaderno.getNombre(), cuaderno.getDescripcion(), cuaderno.getUsuario().getPublicId(),cuaderno.getFechaCreacion(),cuaderno.getFechaUltimaModificacion());
    }

    @Override
    public void actualizarEntidad(Cuaderno cuaderno, CuadernoUpdateDTO dto) {

        aplicarSiValido(dto.nombre(),cuaderno.getNombre(),cuaderno::setNombre);

        aplicarSiValido(dto.descripcion(),cuaderno.getDescripcion(),cuaderno::setDescripcion);
    }
}
