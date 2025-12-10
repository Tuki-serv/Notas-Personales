package com.NotasPersonales.Notas_Personales.Repositories;

import com.NotasPersonales.Notas_Personales.Entities.Nota;

import java.util.Optional;

public interface NotaRepository extends BaseRepository<Nota, Long>{
    Optional<Nota> findByCuaderno (Long cuadernoId);
}
