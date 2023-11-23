package py.com.code100.core.domain.bases;

import py.com.code100.core.models.GetEntitiesResponse;
import py.com.code100.core.specification.Criteria;

import java.util.UUID;

/**
 * A base repository interface defining common CRUD operations for entities.
 *
 * <p>This interface provides methods for adding, deleting, updating, retrieving, and counting entities.
 * It also includes a method to retrieve all entities based on specified criteria.</p>
 *
 * @param <TEntity> The type of the entity extending {@link BaseEntity}.
 */
public interface BaseRepository<TEntity extends BaseEntity> {

    /**
     * Adds a new entity to the repository.
     *
     * @param entity The entity to be added.
     * @return The added entity.
     */
    TEntity add(TEntity entity);

    /**
     * Deletes an entity from the repository based on its unique identifier.
     *
     * @param id The unique identifier of the entity to be deleted.
     */
    void delete(UUID id);

    /**
     * Updates an existing entity in the repository.
     *
     * @param entity The entity to be updated.
     * @return The updated entity.
     */
    TEntity update(TEntity entity);

    /**
     * Retrieves an entity from the repository based on its unique identifier.
     *
     * @param id The unique identifier of the entity to be retrieved.
     * @return The retrieved entity, or {@code null} if not found.
     */
    TEntity getById(UUID id);

    /**
     * Counts the total number of entities in the repository.
     *
     * @return The total number of entities.
     */
    long count();

    /**
     * Retrieves all entities from the repository based on specified criteria.
     *
     * @param criteria The criteria to filter entities.
     * @return A response containing a list of entities that match the criteria.
     */
    GetEntitiesResponse<TEntity> getAll(Criteria criteria);
}
