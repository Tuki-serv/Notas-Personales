package com.NotasPersonales.Notas_Personales.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;
import java.util.UUID;

@NoRepositoryBean
public interface BaseRepository <E, Long> extends JpaRepository<E, Long> {
    Optional<E> findById(Long id);
    Optional<E> findByPublicId(UUID publicId);
}