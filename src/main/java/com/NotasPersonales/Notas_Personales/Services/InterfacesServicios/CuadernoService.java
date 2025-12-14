package com.NotasPersonales.Notas_Personales.Services.InterfacesServicios;

import java.util.List;
import java.util.UUID;

import com.NotasPersonales.Notas_Personales.Entities.Enums.Estado;
import org.springframework.http.ResponseEntity;

import com.NotasPersonales.Notas_Personales.Entities.Cuaderno;
import com.NotasPersonales.Notas_Personales.Entities.DTOs.CuadernosDTOs.CuadernoPostDTO;
import com.NotasPersonales.Notas_Personales.Entities.DTOs.CuadernosDTOs.CuadernoUpdateDTO;
import com.NotasPersonales.Notas_Personales.Entities.DTOs.CuadernosDTOs.CuadernoRespuestaDTO;

public interface CuadernoService extends BaseService<Cuaderno, CuadernoPostDTO, CuadernoUpdateDTO, CuadernoRespuestaDTO> {
    List<CuadernoRespuestaDTO> filtrarPorUsuario (UUID usuarioPublicId, Estado estadoSolicitado);
    ResponseEntity<CuadernoRespuestaDTO> registrar(CuadernoPostDTO dto);
    ResponseEntity<CuadernoRespuestaDTO> actualizar(UUID publicId, CuadernoUpdateDTO dto);
}
