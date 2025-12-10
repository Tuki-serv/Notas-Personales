package com.NotasPersonales.Notas_Personales.Entities.Mappers;

import com.NotasPersonales.Notas_Personales.Entities.DTOs.UsuarioDTOs.UsuarioPostDTO;
import com.NotasPersonales.Notas_Personales.Entities.DTOs.UsuarioDTOs.UsuarioRespuestaDTO;
import com.NotasPersonales.Notas_Personales.Entities.DTOs.UsuarioDTOs.UsuarioUpdateDTO;
import com.NotasPersonales.Notas_Personales.Entities.Usuario;
import com.NotasPersonales.Notas_Personales.Utils.PasswordHasher;

public class UsuarioMapper implements BaseMapper <Usuario, UsuarioPostDTO, UsuarioUpdateDTO, UsuarioRespuestaDTO>{
    @Override
    public Usuario dtoToEntity(UsuarioPostDTO dto) {
        return Usuario.builder()
                .nombre(dto.nombre())
                .email(dto.email())
                .password(PasswordHasher.hash(dto.password()))
                .build();
    }

    @Override
    public UsuarioRespuestaDTO entityToDTO(Usuario usuario) {
        return new UsuarioRespuestaDTO(usuario.getId(),usuario.getNombre(),usuario.getEmail());
    }

    @Override
    public void actulizarEntidad(Usuario usuario, UsuarioUpdateDTO dto) {
        if (dto.nombre() != null && !dto.nombre().isBlank() && !dto.nombre().equals(usuario.getNombre())){
            usuario.setNombre(dto.nombre());
        }

        if (dto.email() != null && !dto.email().isBlank() && !dto.email().equals(usuario.getEmail())){
            usuario.setEmail(dto.email());
        }

        if (dto.password() != null && !dto.password().isBlank()){
            String passwordHash = PasswordHasher.hash(dto.password());
            if(!passwordHash.equals(usuario.getPassword())){
                usuario.setPassword(passwordHash);
            }
        }
    }
}
