package com.NotasPersonales.Notas_Personales.Entities.Mappers;

import com.NotasPersonales.Notas_Personales.Entities.DTOs.NotaDTOs.NotaPostDTO;
import com.NotasPersonales.Notas_Personales.Entities.DTOs.NotaDTOs.NotaRespuestaDTO;
import com.NotasPersonales.Notas_Personales.Entities.DTOs.NotaDTOs.NotaUpdateDTO;
import com.NotasPersonales.Notas_Personales.Entities.Nota;
import org.springframework.stereotype.Component;

@Component
public class NotaMapper implements BaseMapper <Nota, NotaPostDTO, NotaUpdateDTO, NotaRespuestaDTO>{

    @Override
    public Nota dtoToEntity(NotaPostDTO dto) {
        return Nota.builder()
                .titulo(dto.titulo())
                .contenido(dto.contenido())
                .build();
    }

    @Override
    public NotaRespuestaDTO entityToDTO(Nota nota) {
        return new NotaRespuestaDTO(nota.getPublicId(), nota.getTitulo(), nota.getContenido(), nota.getCuaderno().getPublicId(), nota.getFechaCreacion(), nota.getFechaUltimaModificacion());
    }

    @Override
    public void actualizarEntidad(Nota nota, NotaUpdateDTO dto) {

        aplicarSiValido(dto.titulo(),dto.titulo(),nota::setTitulo);

        aplicarSiValido(dto.contenido(),dto.contenido(),nota::setContenido);
    }
}
