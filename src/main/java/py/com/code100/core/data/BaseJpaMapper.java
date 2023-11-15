package py.com.code100.core.data;

import org.hibernate.Hibernate;
import org.mapstruct.Condition;
import org.mapstruct.Context;
import org.mapstruct.InheritInverseConfiguration;
import org.springframework.data.domain.Page;
import py.com.code100.core.annotations.DoIgnore;
import py.com.code100.core.models.CycleAvoidingMappingContext;

import java.util.Collection;
import java.util.List;

import java.util.Collection;
import java.util.List;

/**
 * An interface defining base mapping methods between JPA entities and domain models.
 *
 * <p>Implementations of this interface provide methods for converting between JPA entities and domain models.
 * It uses MapStruct annotations to define mappings.</p>
 *
 * @param <D> The type of the domain model.
 * @param <JPA> The type of the JPA entity.
 */
public interface BaseJpaMapper<D, JPA> {

    /**
     * Converts a JPA entity to a domain model with the provided mapping context.
     *
     * @param jpaModel The JPA entity to be converted.
     * @param cycleAvoidingMappingContext The mapping context to avoid cycles in mappings.
     * @return The converted domain model.
     */
    D toDomainModel(JPA jpaModel, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    /**
     * Converts a JPA entity to a domain model using a default mapping context.
     *
     * <p>This method serves as a default implementation, using a new instance of {@link CycleAvoidingMappingContext}
     * as the mapping context to avoid cycles in mappings.</p>
     *
     * @param jpaModel The JPA entity to be converted.
     * @return The converted domain model.
     */
    @DoIgnore
    default D toDomainModel(JPA jpaModel) {
        return toDomainModel(jpaModel, new CycleAvoidingMappingContext());
    }

    /**
     * Converts a domain model to a JPA entity with the provided mapping context.
     *
     * @param domainModel The domain model to be converted.
     * @param cycleAvoidingMappingContext The mapping context to avoid cycles in mappings.
     * @return The converted JPA entity.
     */
    @InheritInverseConfiguration
    JPA toJpaModel(D domainModel, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    /**
     * Converts a domain model to a JPA entity using a default mapping context.
     *
     * <p>This method serves as a default implementation, using a new instance of {@link CycleAvoidingMappingContext}
     * as the mapping context to avoid cycles in mappings.</p>
     *
     * @param domainModel The domain model to be converted.
     * @return The converted JPA entity.
     */
    @DoIgnore
    default JPA toJpaModel(D domainModel) {
        return toJpaModel(domainModel, new CycleAvoidingMappingContext());
    }

    /**
     * Converts a list of JPA entities to a list of domain models with the provided mapping context.
     *
     * @param list The list of JPA entities to be converted.
     * @param cycleAvoidingMappingContext The mapping context to avoid cycles in mappings.
     * @return The list of converted domain models.
     */
    List<D> toDomainModel(List<JPA> list, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    /**
     * Converts a list of JPA entities to a list of domain models using a default mapping context.
     *
     * <p>This method serves as a default implementation, using a new instance of {@link CycleAvoidingMappingContext}
     * as the mapping context to avoid cycles in mappings.</p>
     *
     * @param list The list of JPA entities to be converted.
     * @return The list of converted domain models.
     */
    @DoIgnore
    default List<D> toDomainModel(List<JPA> list) {
        return toDomainModel(list, new CycleAvoidingMappingContext());
    }

    /**
     * Converts a list of domain models to a list of JPA entities with the provided mapping context.
     *
     * @param list The list of domain models to be converted.
     * @param cycleAvoidingMappingContext The mapping context to avoid cycles in mappings.
     * @return The list of converted JPA entities.
     */
    @InheritInverseConfiguration
    List<JPA> toJpaModel(List<D> list, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    /**
     * Converts a list of domain models to a list of JPA entities using a default mapping context.
     *
     * <p>This method serves as a default implementation, using a new instance of {@link CycleAvoidingMappingContext}
     * as the mapping context to avoid cycles in mappings.</p>
     *
     * @param list The list of domain models to be converted.
     * @return The list of converted JPA entities.
     */
    @DoIgnore
    default List<JPA> toJpaModel(List<D> list) {
        return toJpaModel(list, new CycleAvoidingMappingContext());
    }

    /**
     * Converts a {@link Page} of JPA entities to a {@link Page} of domain models.
     *
     * @param page The {@link Page} of JPA entities to be converted.
     * @return The converted {@link Page} of domain models.
     */
    default Page<D> toDomainModel(Page<JPA> page) {
        return page.map(entity -> toDomainModel(entity, new CycleAvoidingMappingContext()));
    }

    /**
     * Checks if a lazy-loaded collection is available and initialized.
     *
     * @param collection The collection to check.
     * @param <T> The type of elements in the collection.
     * @return {@code true} if the collection is not null and is initialized; {@code false} otherwise.
     */
    @Condition
    default <T> boolean isLazyCollectionAvailable(Collection<T> collection) {
        if (collection == null) {
            return false;
        }
        return Hibernate.isInitialized(collection);
    }
}
