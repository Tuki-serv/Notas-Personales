package com.NotasPersonales.Notas_Personales.Entities.Mappers;

import com.NotasPersonales.Notas_Personales.Entities.DTOs.UsuarioDTOs.UsuarioPostDTO;
import com.NotasPersonales.Notas_Personales.Entities.DTOs.UsuarioDTOs.UsuarioRespuestaDTO;
import com.NotasPersonales.Notas_Personales.Entities.DTOs.UsuarioDTOs.UsuarioUpdateDTO;
import com.NotasPersonales.Notas_Personales.Entities.Usuario;

public class UsuarioMapper implements BaseMapper <Usuario, UsuarioPostDTO, UsuarioUpdateDTO, UsuarioRespuestaDTO>{
    @Override
    public Usuario dtoToEntity(UsuarioPostDTO usuarioPostDTO) {
        return null;
    }

    @Override
    public UsuarioRespuestaDTO entityToDTO(Usuario usuario) {
        return null;
    }

    @Override
    public void actulizarEntidad(Usuario usuario, UsuarioUpdateDTO usuarioUpdateDTO) {

    }
}
