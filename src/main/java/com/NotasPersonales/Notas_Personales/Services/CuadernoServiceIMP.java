package com.NotasPersonales.Notas_Personales.Services;

import com.NotasPersonales.Notas_Personales.Entities.Cuaderno;
import com.NotasPersonales.Notas_Personales.Entities.DTOs.CuadernosDTOs.CuadernoPostDTO;
import com.NotasPersonales.Notas_Personales.Entities.DTOs.CuadernosDTOs.CuadernoRespuestaDTO;
import com.NotasPersonales.Notas_Personales.Entities.DTOs.CuadernosDTOs.CuadernoUpdateDTO;
import com.NotasPersonales.Notas_Personales.Entities.Mappers.CuadernoMapper;
import com.NotasPersonales.Notas_Personales.Repositories.CuadernoRepository;
import com.NotasPersonales.Notas_Personales.Services.InterfacesServicios.CuadernoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

public class CuadernoServiceIMP extends BaseServiceIMP <Cuaderno, CuadernoPostDTO, CuadernoUpdateDTO, CuadernoRespuestaDTO> implements CuadernoService {
    @Autowired
    CuadernoRepository cuadernoRepository;
    @Autowired
    CuadernoMapper cuadernoMapper;

    @Override
    public ResponseEntity<CuadernoRespuestaDTO> registrar(CuadernoPostDTO dto) {
        boolean existe = cuadernoRepository.findByUsuario_PublicIdAndNombreIgnoreCase(dto.usuarioID(),dto.nombre()).isPresent();

        if (existe) throw new ResponseStatusException(HttpStatus.CONFLICT, "Ya existe un cuaderno con ese nombre");

        return ResponseEntity.status(HttpStatus.CREATED).body(super.crear(dto));
    }

    @Override
    public ResponseEntity<CuadernoRespuestaDTO> actualizar(UUID publicId, CuadernoUpdateDTO dto) {
        Cuaderno cuaderno = buscarEntidadPorPublicId(publicId);
        Optional<Cuaderno> existe = cuadernoRepository.findByUsuario_PublicIdAndNombreIgnoreCase(cuaderno.getPublicId(), dto.nombre());

        if (existe.isPresent() && !existe.get().getPublicId().equals(publicId)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Ya existe un cuaderno con ese nombre");
        }

        return ResponseEntity.ok(super.editar(cuaderno,dto));
    }
}
