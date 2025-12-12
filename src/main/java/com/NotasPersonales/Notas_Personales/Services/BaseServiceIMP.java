package com.NotasPersonales.Notas_Personales.Services;

import com.NotasPersonales.Notas_Personales.Entities.BaseEntity;
import com.NotasPersonales.Notas_Personales.Entities.Mappers.BaseMapper;
import com.NotasPersonales.Notas_Personales.Repositories.BaseRepository;
import com.NotasPersonales.Notas_Personales.Services.InterfacesServicios.BaseService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

public  class BaseServiceIMP <E extends BaseEntity,PostDTO, UpdateDTO, RespuestaDTO> implements BaseService<E,PostDTO, UpdateDTO, RespuestaDTO> {

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
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT, "No encontrado"));
    }

    // -----------------------------------------------------------------------------------------------------

    protected Boolean filtroEstado (String estado){
        return switch (estado.toLowerCase()) {
            case "activos" -> false;
            case "eliminados" -> true;
            default -> null;
        };
    }

    @Override
    public List<RespuestaDTO> listarTodos() {
        return mapear(baseRepository.findAll());
    }

    @Override
    public List<RespuestaDTO> listarPorEstado(Boolean eliminado) {
        return mapear(baseRepository.findByEliminadoOrderByIdAsc(eliminado));
    }

    // -----------------------------------------------------------------------------------------------------

    @Override
    public RespuestaDTO crear(PostDTO dto) {
        E entidad = baseMapper.dtoToEntity(dto);
        return baseMapper.entityToDTO(baseRepository.save(entidad));
    }

    @Override
    public RespuestaDTO editar(UUID publicId,UpdateDTO dto) {
        E entidad = buscarEntidadPorPublicId(publicId);
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
    public void reactivar(UUID publicID) {

    }
}
