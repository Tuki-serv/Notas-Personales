package com.NotasPersonales.Notas_Personales.Services;

import com.NotasPersonales.Notas_Personales.Entities.BaseEntity;
import com.NotasPersonales.Notas_Personales.Entities.DTOs.VersionableDTO;
import com.NotasPersonales.Notas_Personales.Entities.Enums.Estado;
import com.NotasPersonales.Notas_Personales.Entities.Mappers.BaseMapper;
import com.NotasPersonales.Notas_Personales.Repositories.BaseRepository;
import com.NotasPersonales.Notas_Personales.Services.InterfacesServicios.BaseService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

public abstract class BaseServiceIMP <E extends BaseEntity,PostDTO, UpdateDTO extends VersionableDTO, RespuestaDTO> implements BaseService<E,PostDTO, UpdateDTO, RespuestaDTO> {

    @Autowired
    BaseRepository<E> baseRepository;
    @Autowired
    BaseMapper<E,PostDTO, UpdateDTO, RespuestaDTO> baseMapper;

// -----------------------------------------------------------------------------------------------------


    protected List<RespuestaDTO> mapear(List<E> entidades){
        return entidades.stream()
                .map(baseMapper::entityToDTO)
                .toList();
    }

    protected E buscarEntidadPorPublicId (UUID publicId){
        return baseRepository.findByPublicId(publicId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No encontrado"));
    }

    protected void validarVersion (E entidad, UpdateDTO dto){
        if (dto.version() != null && !dto.version().equals(entidad.getVersion())){
            throw new ObjectOptimisticLockingFailureException(entidad.getClass(),
                    entidad.getId().toString());
        }
    }

// -----------------------------------------------------------------------------------------------------

    protected Boolean filtroEstado (Estado estado){
        return switch (estado) {
            case ACTIVO -> false;
            case ELIMINADO -> true;
            case TODOS -> null;
            default -> throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Estado Invalido");
        };
    }

    private List<RespuestaDTO> listarTodos() {
        return mapear(baseRepository.findAllByOrderByIdAsc());
    }

    private List<RespuestaDTO> listarPorEstado(Boolean eliminado) {
        return mapear(baseRepository.findByEliminadoOrderByIdAsc(eliminado));
    }

    @Override
    public List<RespuestaDTO> filtrarPorEstado(Estado estadoSolicitado) {
        Boolean estado = filtroEstado(estadoSolicitado);

        return (estado == null)
                ? listarTodos()
                : listarPorEstado(estado);
    }

// -----------------------------------------------------------------------------------------------------


    protected RespuestaDTO crear(PostDTO dto) {
        E entidad = baseMapper.dtoToEntity(dto);
        return baseMapper.entityToDTO(baseRepository.save(entidad));
    }

    protected RespuestaDTO editar(E entidad,UpdateDTO dto) {
        validarVersion(entidad,dto);
        baseMapper.actualizarEntidad(entidad,dto);
        return baseMapper.entityToDTO(baseRepository.save(entidad));
    }

    @Override
    @Transactional
    public ResponseEntity<RespuestaDTO> eliminar(UUID publicID) {
        E entidad = buscarEntidadPorPublicId(publicID);
        RespuestaDTO dto = baseMapper.entityToDTO(entidad);
        baseRepository.delete(entidad);
        return ResponseEntity.ok(dto);
    }

    @Override
    public ResponseEntity<RespuestaDTO> reactivar(UUID publicID) {
        E entidad = buscarEntidadPorPublicId(publicID);
        entidad.setEliminado(false);
        return ResponseEntity.ok(baseMapper.entityToDTO(baseRepository.save(entidad)));
    }
}
