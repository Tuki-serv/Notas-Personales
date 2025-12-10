package com.NotasPersonales.Notas_Personales.Repositories;

import com.NotasPersonales.Notas_Personales.Entities.Cuaderno;
import com.NotasPersonales.Notas_Personales.Entities.Usuario;

import java.util.List;
import java.util.UUID;

public interface CuadernoRepository extends BaseRepository<Cuaderno, Long>{
    List<Cuaderno> findByUsuario_PublicId(UUID publicId);
    List<Cuaderno> findByUsuario(Usuario usuario);
}
