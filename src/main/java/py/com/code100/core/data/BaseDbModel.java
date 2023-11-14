package py.com.code100.core.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

/**
 * An abstract base class for database models.
 *
 * <p>This class is annotated with {@link Entity}, making it eligible for persistence in a relational database.</p>
 * <p>It utilizes Lombok annotations, such as {@link Data}, {@link SuperBuilder}, {@link AllArgsConstructor}, and
 * {@link RequiredArgsConstructor}, to automatically generate boilerplate code for getters, setters, builders, and constructors.</p>
 * <p>The {@link Id} annotation indicates the primary key, and the {@link UuidGenerator} annotation is used to generate
 * UUIDs for the ID field.</p>
 * <p>The {@link CreationTimestamp} annotation automatically populates the {@code created_at} field with the current timestamp
 * upon insertion, while the {@link UpdateTimestamp} annotation does the same for the {@code updated_at} field.</p>
 * <p>The {@link Component} annotation indicates that this class is a Spring component.</p>
 * <p>The {@link Inheritance} annotation specifies the inheritance strategy as {@link InheritanceType#TABLE_PER_CLASS}.</p>
 */
@Entity
@Data
@SuperBuilder
@Component
@AllArgsConstructor
@RequiredArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class BaseDbModel {

    /**
     * The unique identifier for the entity.
     */
    @Id
    @UuidGenerator(style = UuidGenerator.Style.AUTO)
    @GeneratedValue
    protected UUID id;

    /**
     * Indicates whether the entity is active.
     */
    @Column(nullable = false)
    protected boolean isActive;

    /**
     * The timestamp representing the creation date and time of the entity.
     */
    @CreationTimestamp
    @Column(name = "created_at", insertable = false, updatable = false, nullable = false)
    protected Date createAt;

    /**
     * The timestamp representing the last update date and time of the entity.
     */
    @UpdateTimestamp
    @Column(name = "updated_at", insertable = false)
    protected Date updateAt;

    /**
     * The timestamp representing the deletion date and time of the entity (if deleted).
     */
    @Column(name = "deleted_at")
    protected Date deleteAt;
}
