package com.NotasPersonales.Notas_Personales.Entities.Mappers;

import com.NotasPersonales.Notas_Personales.Entities.DTOs.NotaDTOs.NotaPostDTO;
import com.NotasPersonales.Notas_Personales.Entities.DTOs.NotaDTOs.NotaRespuestaDTO;
import com.NotasPersonales.Notas_Personales.Entities.DTOs.NotaDTOs.NotaUpdateDTO;
import com.NotasPersonales.Notas_Personales.Entities.Nota;

public class NotaMapper implements BaseMapper <Nota, NotaPostDTO, NotaUpdateDTO, NotaRespuestaDTO>{

    @Override
    public Nota dtoToEntity(NotaPostDTO notaPostDTO) {
        return null;
    }

    @Override
    public NotaRespuestaDTO entityToDTO(Nota nota) {
        return null;
    }

    @Override
    public void actulizarEntidad(Nota nota, NotaUpdateDTO notaUpdateDTO) {

    }
}
