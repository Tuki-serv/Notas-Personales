package com.NotasPersonales.Notas_Personales.Services.InterfacesServicios;

import com.NotasPersonales.Notas_Personales.Entities.DTOs.ContenidoNotaDTOs.ContenidoNotaRespuestaDTO;
import com.NotasPersonales.Notas_Personales.Entities.DTOs.ContenidoNotaDTOs.ContenidoNotaUpdateDTO;
import com.NotasPersonales.Notas_Personales.Entities.DTOs.NotaDTOs.*;
import com.NotasPersonales.Notas_Personales.Entities.Enums.Estado;
import com.NotasPersonales.Notas_Personales.Entities.Nota;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface NotaService extends BaseService <Nota, NotaPostDTO, NotaUpdateDTO, NotaRespuestaDTO>{
    List<NotaRespuestaDTO> filtraPorCuaderno(UUID cuadernoPublicId, Estado estadoSolicitado);
    ResponseEntity<NotaRespuestaDTO> registrar (NotaPostDTO dto);
    ResponseEntity<NotaRespuestaDTO> actualizar (UUID publicId,NotaUpdateDTO dto);
    ResponseEntity<ContenidoNotaRespuestaDTO> obtenerContenido (UUID publicId);
    ResponseEntity<ContenidoNotaRespuestaDTO> actualizarContenido (UUID publicId, ContenidoNotaUpdateDTO dto);
}
