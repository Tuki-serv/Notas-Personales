package com.NotasPersonales.Notas_Personales.Services;

import com.NotasPersonales.Notas_Personales.Entities.Cuaderno;
import com.NotasPersonales.Notas_Personales.Entities.DTOs.NotaDTOs.NotaPostDTO;
import com.NotasPersonales.Notas_Personales.Entities.DTOs.NotaDTOs.NotaRespuestaDTO;
import com.NotasPersonales.Notas_Personales.Entities.DTOs.NotaDTOs.NotaUpdateDTO;
import com.NotasPersonales.Notas_Personales.Entities.Enums.Estado;
import com.NotasPersonales.Notas_Personales.Entities.Nota;
import com.NotasPersonales.Notas_Personales.Repositories.CuadernoRepository;
import com.NotasPersonales.Notas_Personales.Repositories.NotaRepository;
import com.NotasPersonales.Notas_Personales.Services.InterfacesServicios.NotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class NotaServiceIMP extends BaseServiceIMP <Nota, NotaPostDTO, NotaUpdateDTO, NotaRespuestaDTO> implements NotaService {
    @Autowired
    NotaRepository notaRepository;
    @Autowired
    CuadernoRepository cuadernoRepository;

    @Override
    public List<NotaRespuestaDTO> filtraPorCuaderno(UUID cuadernoPublicId, Estado estadoSolicitado) {
        Boolean estado = filtroEstado(estadoSolicitado);

        return mapear(
                (estado == null)
                ? notaRepository.findByCuaderno_PublicIdOrderByIdAsc(cuadernoPublicId)
                        : notaRepository.findByCuaderno_PublicIdAndEliminadoOrderByIdAsc(cuadernoPublicId,estado)
        );
    }

    @Override
    public NotaRespuestaDTO crear(NotaPostDTO dto){
        Cuaderno cuaderno = cuadernoRepository.findByPublicId(dto.cuadernoID())
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cuaderno no encontrado"));
        Nota nota = baseMapper.dtoToEntity(dto);

        nota.setCuaderno(cuaderno);

        return baseMapper.entityToDTO(notaRepository.save(nota));
    }

    @Override
    public ResponseEntity<NotaRespuestaDTO> registrar(NotaPostDTO dto) {
        notaRepository.findByCuaderno_PublicIdAndTituloIgnoreCase(dto.cuadernoID(), dto.titulo())
                .ifPresent(present -> {
                    throw new ResponseStatusException(HttpStatus.CONFLICT, "Ya existe una nota con ese nombre");
                });

        return ResponseEntity.status(HttpStatus.CREATED).body(crear(dto));
    }

    @Override
    public ResponseEntity<NotaRespuestaDTO> actualizar(UUID publicId, NotaUpdateDTO dto) {
        Nota nota = buscarEntidadPorPublicId(publicId);

        Optional<Nota> existe = notaRepository.findByCuaderno_PublicIdAndTituloIgnoreCase(nota.getCuaderno().getPublicId(),dto.titulo());

        if (existe.isPresent() && !existe.get().getPublicId().equals(publicId)){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Y existe una nota con ese nombre");
        }

        return ResponseEntity.ok(editar(nota,dto));
    }
}
