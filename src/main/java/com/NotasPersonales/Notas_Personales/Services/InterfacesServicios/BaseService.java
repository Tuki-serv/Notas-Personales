package com.NotasPersonales.Notas_Personales.Services.InterfacesServicios;

import com.NotasPersonales.Notas_Personales.Entities.Enums.Estado;

import java.util.List;
import java.util.UUID;

public interface BaseService <E,PostDTO, UpdateDTO, RespuestaDTO> {
    List<RespuestaDTO> filtrarPorEstado(Estado estadoSolicitado);
    RespuestaDTO crear(PostDTO dto);
    RespuestaDTO editar(E entidad, UpdateDTO dto);
    RespuestaDTO eliminar(UUID publicID);
    RespuestaDTO reactivar(UUID publicID);
}
