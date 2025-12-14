package com.NotasPersonales.Notas_Personales.Services;

import com.NotasPersonales.Notas_Personales.Entities.DTOs.NotaDTOs.NotaPostDTO;
import com.NotasPersonales.Notas_Personales.Entities.DTOs.NotaDTOs.NotaRespuestaDTO;
import com.NotasPersonales.Notas_Personales.Entities.DTOs.NotaDTOs.NotaUpdateDTO;
import com.NotasPersonales.Notas_Personales.Entities.Mappers.NotaMapper;
import com.NotasPersonales.Notas_Personales.Entities.Nota;
import com.NotasPersonales.Notas_Personales.Repositories.NotaRepository;
import com.NotasPersonales.Notas_Personales.Services.InterfacesServicios.NotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

public class NotaServiceIMP extends BaseServiceIMP <Nota, NotaPostDTO, NotaUpdateDTO, NotaRespuestaDTO> implements NotaService {
    @Autowired
    NotaRepository notaRepository;
    @Autowired
    NotaMapper notaMapper;

    @Override
    public ResponseEntity<NotaRespuestaDTO> registrar(NotaPostDTO dto) {
        boolean existe = notaRepository.findByCuaderno_PublicIdAndTituloIgnoreCase(dto.cuadernoID(), dto.titulo()).isPresent();

        if (existe) throw new ResponseStatusException(HttpStatus.CONFLICT, "Ya existe una nota con ese nombre");

        return ResponseEntity.status(HttpStatus.CREATED).body(super.crear(dto));
    }

    @Override
    public ResponseEntity<NotaRespuestaDTO> actualizar(UUID publicId, NotaUpdateDTO dto) {
        Nota nota = buscarEntidadPorPublicId(publicId);

        Optional<Nota> existe = notaRepository.findByCuaderno_PublicIdAndTituloIgnoreCase(nota.getPublicId(),dto.titulo());

        if (existe.isPresent() && !existe.get().getPublicId().equals(publicId)){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Y existe una nota con ese nombre");
        }

        return ResponseEntity.ok(super.editar(nota,dto));
    }
}
