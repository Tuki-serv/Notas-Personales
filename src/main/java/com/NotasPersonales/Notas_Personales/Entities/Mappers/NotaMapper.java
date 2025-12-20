package com.NotasPersonales.Notas_Personales.Entities.Mappers;

import com.NotasPersonales.Notas_Personales.Entities.ContenidoNota;
import com.NotasPersonales.Notas_Personales.Entities.DTOs.ContenidoNotaDTOs.ContenidoNotaRespuestaDTO;
import com.NotasPersonales.Notas_Personales.Entities.DTOs.ContenidoNotaDTOs.ContenidoNotaUpdateDTO;
import com.NotasPersonales.Notas_Personales.Entities.DTOs.NotaDTOs.*;
import com.NotasPersonales.Notas_Personales.Entities.Nota;
import org.springframework.stereotype.Component;

@Component
public class NotaMapper implements BaseMapper <Nota, NotaPostDTO, NotaUpdateDTO, NotaRespuestaDTO>{

    @Override
    public Nota dtoToEntity(NotaPostDTO dto) {
        return Nota.builder()
                .titulo(dto.titulo())
                .build();
    }

    public ContenidoNotaRespuestaDTO entityToContenidoDTO(Nota nota){
        String texto = nota.getContenido().getTexto();

        return new ContenidoNotaRespuestaDTO(
                nota.getPublicId(),
                nota.getTitulo(),
                texto,
                nota.getCuaderno().getPublicId(),
                nota.getVersion(),
                nota.getFechaCreacion(),
                nota.getFechaUltimaModificacion()
        );
    }

    @Override
    public NotaRespuestaDTO entityToDTO(Nota nota) {
        return new NotaRespuestaDTO(
                nota.getPublicId(),
                nota.getTitulo(),
                nota.getCuaderno().getPublicId(),
                nota.getVersion(),
                nota.getFechaCreacion(),
                nota.getFechaUltimaModificacion());
    }

    @Override
    public void actualizarEntidad(Nota nota, NotaUpdateDTO dto) {
        aplicarSiValido(dto.titulo(),nota.getTitulo(),nota::setTitulo);
    }

    public void actualizarContenido(ContenidoNota contenido , ContenidoNotaUpdateDTO dto){
        aplicarSiValido(dto.texto(), contenido.getTexto(), contenido::setTexto);
    }
}
