package py.com.code100.core.domain.bases;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.Date;
import java.util.UUID;

/**
 * A base entity class providing common fields for entities in the application.
 *
 * <p>This class is annotated with Lombok annotations, {@link Data} and {@link SuperBuilder}, to automatically generate
 * boilerplate code for getters, setters, builders, and constructors.</p>
 *
 * <p>The fields in this class include:</p>
 * <ul>
 *     <li>{@code id}: The unique identifier for the entity.</li>
 *     <li>{@code isActive}: A flag indicating whether the entity is active.</li>
 *     <li>{@code createAt}: The timestamp representing the creation date and time of the entity.</li>
 *     <li>{@code updateAt}: The timestamp representing the last update date and time of the entity.</li>
 *     <li>{@code deleteAt}: The timestamp representing the deletion date and time of the entity (if deleted).</li>
 * </ul>
 *
 * <p>This class serves as a common base for entities in the application.</p>
 */
@Data
@SuperBuilder
public class BaseEntity {

    /**
     * The unique identifier for the entity.
     */
    protected UUID id;

    /**
     * Indicates whether the entity is active.
     */
    protected boolean isActive;

    /**
     * The timestamp representing the creation date and time of the entity.
     */
    protected Date createAt;

    /**
     * The timestamp representing the last update date and time of the entity.
     */
    protected Date updateAt;

    /**
     * The timestamp representing the deletion date and time of the entity (if deleted).
     */
    protected Date deleteAt;
}
