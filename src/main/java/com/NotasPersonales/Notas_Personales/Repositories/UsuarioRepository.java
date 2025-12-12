package com.NotasPersonales.Notas_Personales.Repositories;

import com.NotasPersonales.Notas_Personales.Entities.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends BaseRepository<Usuario>{
    Optional<Usuario> findByEmail(String email);

    Optional<Usuario> findByNombre(String nombre);
}
