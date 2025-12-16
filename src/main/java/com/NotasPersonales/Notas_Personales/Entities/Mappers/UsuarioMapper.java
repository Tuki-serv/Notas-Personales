package com.NotasPersonales.Notas_Personales.Entities.Mappers;

import com.NotasPersonales.Notas_Personales.Entities.DTOs.UsuarioDTOs.UsuarioPostDTO;
import com.NotasPersonales.Notas_Personales.Entities.DTOs.UsuarioDTOs.UsuarioRespuestaDTO;
import com.NotasPersonales.Notas_Personales.Entities.DTOs.UsuarioDTOs.UsuarioUpdateDTO;
import com.NotasPersonales.Notas_Personales.Entities.Usuario;
import com.NotasPersonales.Notas_Personales.Utils.PasswordHasher;
import org.springframework.stereotype.Component;

@Component
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
        return new UsuarioRespuestaDTO(usuario.getPublicId(),usuario.getNombre(),usuario.getEmail(),usuario.getFechaCreacion(),usuario.getFechaUltimaModificacion());
    }

    @Override
    public void actualizarEntidad(Usuario usuario, UsuarioUpdateDTO dto) {

        aplicarSiValido(dto.nombre(),usuario.getNombre(),usuario::setNombre);

        aplicarSiValido(dto.email(),usuario.getEmail(),usuario::setEmail);

        if (dto.password() != null && !dto.password().isBlank()){
            String passwordHash = PasswordHasher.hash(dto.password());
            if(!passwordHash.equals(usuario.getPassword())){
                usuario.setPassword(passwordHash);
            }
        }
    }
}
