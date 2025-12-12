package com.NotasPersonales.Notas_Personales.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * Clase base abstracta para todas las entidades de la aplicación.
 * Define campos comunes para la persistencia, el control de concurrencia
 * y las "soft deletes".
 * <p>
 * Utiliza las anotaciones de Lombok {@code @Getter} y {@code @Setter} para
 * generar automáticamente los métodos de acceso.
 * </p>
 * @author TuNombre
 * @version 1.0
 * @since 10-12-2025
 */
@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity {

    /**
     * Identificador primario de la base de datos.
     * <p>Es el ID técnico, interno, autoincremental y no debe ser expuesto en la API.</p>
     * @return Identificador técnico ({@code Long}).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * ======================================================================
     * COMPARATIVA DE ESTRATEGIAS PARA ID PÚBLICO (publicId)
     * ======================================================================
     *
     * <h3>1. Caso String (El Básico) - DESCARTADO</h3>
     * <ul>
     * <li><b>Uso:</b> Usas el tipo de dato {@code String}.</li>
     * <li><b>Base de Datos:</b> Se guarda como {@code VARCHAR(36)}.</li>
     * <li><b>Ventaja:</b> Muy fácil de leer y debugear.</li>
     * <li><b>Desventaja:</b> Java gasta más memoria y no hay validación de tipo (podrías guardar "hola").</li>
     * </ul>
     *
     * <p><b>Ejemplo de implementación:</b></p>
     * <pre>{@code
     * @Column(unique = true, nullable = false, updatable = false)
     * private String publicId;
     * // Además, requiere este método en la Entidad:
     * @PrePersist
     * protected void onCreate() {
     * if (this.publicId == null) {
     * // Se convierte en string
     * this.publicId = UUID.randomUUID().toString();
     * }
     * }
     * }</pre>
     *
     * <hr>
     *
     * <h3>2. Caso UUID con {@code @JdbcTypeCode} (El Híbrido Recomendado) - DESCARTADO</h3>
     * <ul>
     * <li><b>Java:</b> Usas {@code java.util.UUID} (Tipado fuerte y seguro).</li>
     * <li><b>Base de Datos:</b> Fuerzas a que se guarde como {@code VARCHAR(36)} (Legible para humanos).</li>
     * <li><b>Ventaja:</b> Tienes la seguridad de Java y la facilidad de lectura en la base de datos
     * (ideal para MySQL si quieres leer los datos a simple vista).</li>
     * </ul>
     *
     * <p><b>Ejemplo de implementación:</b></p>
     * <pre>{@code
     * @Column(unique = true, nullable = false, updatable = false)
     * @JdbcTypeCode(SqlTypes.VARCHAR) // La magia: Java ve UUID, DB ve Texto
     * private UUID publicId;
     * }</pre>
     *
     * <hr>
     *
     * <h3>3. Caso UUID Nativo (El Eficiente) - OPCIÓN ELEGIDA</h3>
     * <ul>
     * <li><b>Java:</b> Se utiliza la clase {@code java.util.UUID}.</li>
     * <li><b>Base de Datos:</b>
     * <ul>
     * <li>PostgreSQL: Usa el tipo nativo {@code uuid}.</li>
     * <li>MySQL: Hibernate lo guarda como {@code BINARY(16)} (ilegible para humanos, pero muy rápido).</li>
     * </ul>
     * </li>
     * <li><b>Ventaja:</b> Máxima eficiencia de almacenamiento y tipado fuerte en Java.</li>
     * </ul>
     *
     * <p><b>Implementación actual (Ver código debajo):</b></p>
     * <pre>{@code
     * @Column(name = "public_id", unique = true, nullable = false, updatable = false)
     * private UUID publicId;
     * }</pre>
     *
     * Identificador público único (UUID) que se utiliza para exponer la entidad
     * a clientes externos (APIs). Esto oculta el {@code id} interno.
     */
    @Column(name = "public_id", unique = true, nullable = false, updatable = false)
    private UUID publicId;

    /**
     * Campo de control de concurrencia optimista.
     * Se incrementa automáticamente con cada actualización.
     * @see jakarta.persistence.Version
     */
    @Version
    private Long version;

    /**
     * Indicador de "soft delete". Permite marcar una entidad como
     * eliminada sin borrarla físicamente de la base de datos.
     */
    protected Boolean eliminado = false;

    /**
     * Sello de tiempo que registra cuándo se creó la entidad.
     */
    private LocalDateTime fechaCreacion;

    /**
     * Sello de tiempo que registra la última modificación de la entidad.
     */
    private LocalDateTime fechaUltimaModificacion;

    /**
     * Método ejecutado antes de la persistencia ({@code INSERT}).
     * Inicializa el {@code publicId} con un UUID aleatorio si es {@code null}.
     * También establece {@code fechaCreacion} y {@code fechaUltimaModificacion}.
     * @see jakarta.persistence.PrePersist
     */
    @PrePersist
    protected void onCreate(){
        if (this.publicId == null){
            this.publicId = UUID.randomUUID();
        }

        this.fechaCreacion = LocalDateTime.now();
        this.fechaUltimaModificacion = LocalDateTime.now();
    }

    /**
     * Método ejecutado antes de cualquier actualización ({@code UPDATE}).
     * Actualiza {@code fechaUltimaModificacion} con la hora actual.
     * @see jakarta.persistence.PreUpdate
     */
    @PreUpdate
    protected void onUpdate(){
        this.fechaUltimaModificacion = LocalDateTime.now();
    }

    /**
     * Implementación del método {@code equals(Object o)}.
     * Dos entidades son consideradas iguales si ambas tienen un {@code id}
     * no nulo y es el mismo valor.
     * @param o Objeto a comparar.
     * @return {@code true} si los IDs no nulos son iguales; {@code false} de lo contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseEntity that = (BaseEntity) o;
        // Dos entidades son iguales si tienen el mismo ID (y no es null)
        return id != null && Objects.equals(id, that.id);
    }

    /**
     * Implementación del método {@code hashCode()}.
     * Retorna el hash de la clase para evitar problemas de hash code inmutable
     * cuando el {@code id} aún es {@code null} (antes de ser persistido).
     * @return El código hash de la clase.
     */
    @Override
    public int hashCode() {
        // Retornamos un constante o el hash de la clase para evitar problemas
        // cuando el ID es null antes de persistir.
        return getClass().hashCode();
    }
}