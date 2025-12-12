package com.NotasPersonales.Notas_Personales.Repositories;

import com.NotasPersonales.Notas_Personales.Entities.Cuaderno;
import com.NotasPersonales.Notas_Personales.Entities.Nota;

import java.util.List;
import java.util.UUID;

public interface NotaRepository extends BaseRepository<Nota>{
    List<Nota> findByCuaderno_PublicIdOrderByIdAsc (UUID publicId);

    List<Nota> findByCuaderno_PublicIdAndEliminadoOrderByIdAsc (UUID publicId, Boolean eliminado);

    List<Nota> findByCuadernoOrderByIdAsc (Cuaderno cuaderno);
}
