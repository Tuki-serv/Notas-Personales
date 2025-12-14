package com.NotasPersonales.Notas_Personales.Services.InterfacesServicios;

import com.NotasPersonales.Notas_Personales.Entities.DTOs.UsuarioDTOs.UsuarioLoginDTO;
import com.NotasPersonales.Notas_Personales.Entities.DTOs.UsuarioDTOs.UsuarioPostDTO;
import com.NotasPersonales.Notas_Personales.Entities.DTOs.UsuarioDTOs.UsuarioRespuestaDTO;
import com.NotasPersonales.Notas_Personales.Entities.DTOs.UsuarioDTOs.UsuarioUpdateDTO;
import com.NotasPersonales.Notas_Personales.Entities.Usuario;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface UsuarioService extends BaseService <Usuario, UsuarioPostDTO, UsuarioUpdateDTO, UsuarioRespuestaDTO>{
    ResponseEntity<UsuarioRespuestaDTO> registrarUsuario (UsuarioPostDTO dto);
    ResponseEntity<UsuarioRespuestaDTO> login (UsuarioLoginDTO dto);
    ResponseEntity<UsuarioRespuestaDTO> actualizar (UUID publicId, UsuarioUpdateDTO dto);
}
