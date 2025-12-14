package com.NotasPersonales.Notas_Personales.Services;

import com.NotasPersonales.Notas_Personales.Entities.DTOs.UsuarioDTOs.UsuarioLoginDTO;
import com.NotasPersonales.Notas_Personales.Entities.DTOs.UsuarioDTOs.UsuarioPostDTO;
import com.NotasPersonales.Notas_Personales.Entities.DTOs.UsuarioDTOs.UsuarioRespuestaDTO;
import com.NotasPersonales.Notas_Personales.Entities.DTOs.UsuarioDTOs.UsuarioUpdateDTO;
import com.NotasPersonales.Notas_Personales.Entities.Mappers.UsuarioMapper;
import com.NotasPersonales.Notas_Personales.Entities.Usuario;
import com.NotasPersonales.Notas_Personales.Repositories.UsuarioRepository;
import com.NotasPersonales.Notas_Personales.Services.InterfacesServicios.UsuarioService;
import com.NotasPersonales.Notas_Personales.Utils.PasswordHasher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

public class UsuarioServiceIMP extends BaseServiceIMP <Usuario, UsuarioPostDTO, UsuarioUpdateDTO, UsuarioRespuestaDTO> implements UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    UsuarioMapper usuarioMapper;

    @Override
    public ResponseEntity<UsuarioRespuestaDTO> registrarUsuario(UsuarioPostDTO dto) {
        boolean existe = usuarioRepository.findByEmail(dto.email()).isPresent();

        if (existe) throw new ResponseStatusException(HttpStatus.CONFLICT, "Email ya registrado");

        return ResponseEntity.status(HttpStatus.CREATED).body(super.crear(dto));
    }

    @Override
    public ResponseEntity<UsuarioRespuestaDTO> login(UsuarioLoginDTO dto) {
        Usuario usuario = usuarioRepository.findByEmail(dto.email())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Email no registrado"));

        if (!usuario.getPassword().equals(PasswordHasher.hash(dto.password()))){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciales Invalidas");
        }

        return ResponseEntity.ok(usuarioMapper.entityToDTO(usuario));
    }

    @Override
    public ResponseEntity<UsuarioRespuestaDTO> actualizar(UUID publicId,UsuarioUpdateDTO dto) {
        Usuario usuario = buscarEntidadPorPublicId(publicId);

        Optional<Usuario> existe = usuarioRepository.findByEmail(dto.email());

        if (existe.isPresent() && !existe.get().getPublicId().equals(publicId)){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El Email ya está en uso por otro usuario");
        }

        return ResponseEntity.ok(super.editar(usuario,dto));
    }
}
