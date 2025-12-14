package com.NotasPersonales.Notas_Personales.Repositories;

import com.NotasPersonales.Notas_Personales.Entities.Cuaderno;
import com.NotasPersonales.Notas_Personales.Entities.Usuario;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CuadernoRepository extends BaseRepository<Cuaderno>{
    List<Cuaderno> findByUsuario_PublicIdOrderByIdAsc(UUID publicId);

    List<Cuaderno> findByUsuario_PublicIdAndEliminadoOrderByIdAsc(UUID publicID, Boolean eliminado);

    List<Cuaderno> findByUsuarioOrderByIdAsc(Usuario usuario);

    Optional<Cuaderno> findByUsuario_PublicIdAndNombreIgnoreCase(UUID usuarioPublicId, String nombre);
}
