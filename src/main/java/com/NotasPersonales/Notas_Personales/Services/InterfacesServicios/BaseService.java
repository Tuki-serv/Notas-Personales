package com.NotasPersonales.Notas_Personales.Services.InterfacesServicios;

import java.util.List;
import java.util.UUID;

public interface BaseService <E,PostDTO, UpdateDTO, RespuestaDTO> {
    List<RespuestaDTO> listarTodos();
    List<RespuestaDTO> listarPorEstado(Boolean eliminado);
    RespuestaDTO crear(PostDTO dto);
    RespuestaDTO editar(UUID publicId, UpdateDTO dto);
    RespuestaDTO eliminar(UUID publicID);
    void reactivar(UUID publicID);
}
