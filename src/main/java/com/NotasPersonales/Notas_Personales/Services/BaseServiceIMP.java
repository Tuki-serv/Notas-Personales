package com.NotasPersonales.Notas_Personales.Services;

import com.NotasPersonales.Notas_Personales.Entities.BaseEntity;
import com.NotasPersonales.Notas_Personales.Entities.Enums.Estado;
import com.NotasPersonales.Notas_Personales.Entities.Mappers.BaseMapper;
import com.NotasPersonales.Notas_Personales.Repositories.BaseRepository;
import com.NotasPersonales.Notas_Personales.Services.InterfacesServicios.BaseService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

public abstract class BaseServiceIMP <E extends BaseEntity,PostDTO, UpdateDTO, RespuestaDTO> implements BaseService<E,PostDTO, UpdateDTO, RespuestaDTO> {

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

// -----------------------------------------------------------------------------------------------------

    protected Boolean filtroEstado (Estado estado){
        return switch (estado) {
            case ACTIVOS -> false;
            case ELIMINADO -> true;
            case TODOS -> null;
            default -> throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Estado Invalido");
        };
    }

    private List<RespuestaDTO> listarTodos() {
        return mapear(baseRepository.findAll());
    }

    private List<RespuestaDTO> listarPorEstado(Boolean eliminado) {
        return mapear(baseRepository.findByEliminadoOrderByIdAsc(eliminado));
    }

    @Override
    public List<RespuestaDTO> filtrarPorEstado(Estado estadoSolicitado) {
        if (filtroEstado(estadoSolicitado) == null){
            return listarTodos();
        }else{
            return listarPorEstado(filtroEstado(estadoSolicitado));
        }
    }

// -----------------------------------------------------------------------------------------------------

    @Override
    public RespuestaDTO crear(PostDTO dto) {
        E entidad = baseMapper.dtoToEntity(dto);
        return baseMapper.entityToDTO(baseRepository.save(entidad));
    }

    @Override
    public RespuestaDTO editar(E entidad,UpdateDTO dto) {
        baseMapper.actulizarEntidad(entidad,dto);
        return baseMapper.entityToDTO(baseRepository.save(entidad));
    }

    @Override
    @Transactional
    public RespuestaDTO eliminar(UUID publicID) {
        E entidad = buscarEntidadPorPublicId(publicID);
        RespuestaDTO dto = baseMapper.entityToDTO(entidad);
        baseRepository.delete(entidad);
        return dto;
    }

    @Override
    public RespuestaDTO reactivar(UUID publicID) {
        E entidad = buscarEntidadPorPublicId(publicID);
        entidad.setEliminado(false);
        return baseMapper.entityToDTO(baseRepository.save(entidad));
    }
}
