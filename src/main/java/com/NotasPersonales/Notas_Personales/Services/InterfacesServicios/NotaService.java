package com.NotasPersonales.Notas_Personales.Services.InterfacesServicios;

import com.NotasPersonales.Notas_Personales.Entities.DTOs.NotaDTOs.NotaPostDTO;
import com.NotasPersonales.Notas_Personales.Entities.DTOs.NotaDTOs.NotaRespuestaDTO;
import com.NotasPersonales.Notas_Personales.Entities.DTOs.NotaDTOs.NotaUpdateDTO;
import com.NotasPersonales.Notas_Personales.Entities.Nota;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface NotaService extends BaseService <Nota, NotaPostDTO, NotaUpdateDTO, NotaRespuestaDTO>{
    ResponseEntity<NotaRespuestaDTO> registrar (NotaPostDTO dto);
    ResponseEntity<NotaRespuestaDTO> actualizar (UUID publicId,NotaUpdateDTO dto);
}
