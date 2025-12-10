package com.NotasPersonales.Notas_Personales.Repositories;

import com.NotasPersonales.Notas_Personales.Entities.Cuaderno;

import java.util.Optional;

public interface CuadernoRepository extends BaseRepository<Cuaderno, Long>{
    Optional<Cuaderno> findByUsuario(Long usuarioId);
}
