package com.NotasPersonales.Notas_Personales.Services;

import com.NotasPersonales.Notas_Personales.Entities.Cuaderno;
import com.NotasPersonales.Notas_Personales.Entities.DTOs.CuadernosDTOs.CuadernoPostDTO;
import com.NotasPersonales.Notas_Personales.Entities.DTOs.CuadernosDTOs.CuadernoRespuestaDTO;
import com.NotasPersonales.Notas_Personales.Entities.DTOs.CuadernosDTOs.CuadernoUpdateDTO;
import com.NotasPersonales.Notas_Personales.Entities.Enums.Estado;
import com.NotasPersonales.Notas_Personales.Entities.Usuario;
import com.NotasPersonales.Notas_Personales.Repositories.CuadernoRepository;
import com.NotasPersonales.Notas_Personales.Repositories.UsuarioRepository;
import com.NotasPersonales.Notas_Personales.Services.InterfacesServicios.CuadernoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CuadernoServiceIMP extends BaseServiceIMP <Cuaderno, CuadernoPostDTO, CuadernoUpdateDTO, CuadernoRespuestaDTO> implements CuadernoService {
    @Autowired
    CuadernoRepository cuadernoRepository;
    @Autowired
    UsuarioRepository usuarioRepository;


    @Override
    public List<CuadernoRespuestaDTO> filtrarPorUsuario(UUID usuarioPublicId, Estado estadoSolicitado) {
        Boolean estado = filtroEstado(estadoSolicitado);

        if (estado == null){
            return mapear(cuadernoRepository.findByUsuario_PublicIdOrderByIdAsc(usuarioPublicId));
        }else{
            return mapear(cuadernoRepository.findByUsuario_PublicIdAndEliminadoOrderByIdAsc(usuarioPublicId,estado));
        }
    }

    @Override
    public CuadernoRespuestaDTO crear(CuadernoPostDTO dto){
        Usuario usuario = usuarioRepository.findByPublicId(dto.usuarioID())
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));
        Cuaderno cuaderno = baseMapper.dtoToEntity(dto);

        cuaderno.setUsuario(usuario);
        return baseMapper.entityToDTO(cuadernoRepository.save(cuaderno));
    }

    @Override
    public ResponseEntity<CuadernoRespuestaDTO> registrar(CuadernoPostDTO dto) {
        boolean existe = cuadernoRepository.findByUsuario_PublicIdAndNombreIgnoreCase(dto.usuarioID(),dto.nombre()).isPresent();

        if (existe) throw new ResponseStatusException(HttpStatus.CONFLICT, "Ya existe un cuaderno con ese nombre");

        return ResponseEntity.status(HttpStatus.CREATED).body(crear(dto));
    }

    @Override
    public ResponseEntity<CuadernoRespuestaDTO> actualizar(UUID publicId, CuadernoUpdateDTO dto) {
        Cuaderno cuaderno = buscarEntidadPorPublicId(publicId);
        Optional<Cuaderno> existe = cuadernoRepository.findByUsuario_PublicIdAndNombreIgnoreCase(cuaderno.getUsuario().getPublicId(), dto.nombre());

        if (existe.isPresent() && !existe.get().getPublicId().equals(publicId)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Ya existe un cuaderno con ese nombre");
        }

        return ResponseEntity.ok(super.editar(cuaderno,dto));
    }
}
