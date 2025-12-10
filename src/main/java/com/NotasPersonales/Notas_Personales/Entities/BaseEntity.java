package com.NotasPersonales.Notas_Personales.Entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    protected Boolean activo = true;
    protected Boolean eliminado = false;
    private LocalDate fechaCreacion = LocalDate.now();
    private LocalDate fechaUltimaModificacion;
}
