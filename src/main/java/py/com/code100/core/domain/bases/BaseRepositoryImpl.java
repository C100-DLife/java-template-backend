package py.com.code100.core.domain.bases;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Component;
import py.com.code100.core.data.BaseDbModel;
import py.com.code100.core.data.BaseJpaMapper;
import py.com.code100.core.models.GetEntitiesResponse;
import py.com.code100.core.models.PaginationResponse;
import py.com.code100.core.specification.Criteria;
import py.com.code100.core.specification.JpaSpecificationBuilder;
import py.com.code100.core.specification.OrderType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * An abstract base repository implementation providing common CRUD operations for entities.
 *
 * <p>This class includes methods for adding, deleting, updating, retrieving, counting, and fetching entities based on criteria.</p>
 *
 * @param <TEntity> The type of the entity extending {@link BaseEntity}.
 * @param <TModel> The type of the JPA entity extending {@link BaseDbModel}.
 */
@Component
public abstract class BaseRepositoryImpl<TEntity extends BaseEntity, TModel extends BaseDbModel> {

    /**
     * Adds a new entity to the repository.
     *
     * @param entity The entity to be added.
     * @param repository The JPA repository for the entity.
     * @param modelMapper The mapper for converting between domain and JPA entities.
     * @return The added entity.
     */
    protected TEntity add(TEntity entity, JpaRepository<TModel, UUID> repository, BaseJpaMapper<TEntity, TModel> modelMapper) {
        entity.setCreateAt(new Date());
        entity.setUpdateAt(new Date());
        var model = modelMapper.toJpaModel(entity);
        var newModel = repository.save(model);
        return modelMapper.toDomainModel(newModel);
    }

    /**
     * Deletes an entity from the repository based on its unique identifier.
     *
     * @param id The unique identifier of the entity to be deleted.
     * @param repository The JPA repository for the entity.
     */
    protected void delete(UUID id, JpaRepository<TModel, UUID> repository) {
        var model = repository.findById(id);

        if (model.isPresent()) {
            model.get().setDeleteAt(new Date());
            model.get().setUpdateAt(new Date());
            model.get().setActive(false);

            repository.save(model.get());
        }
    }

    /**
     * Updates an existing entity in the repository.
     *
     * @param entity The entity to be updated.
     * @param repository The JPA repository for the entity.
     * @param modelMapper The mapper for converting between domain and JPA entities.
     * @return The updated entity.
     */
    protected TEntity update(TEntity entity, JpaRepository<TModel, UUID> repository, BaseJpaMapper<TEntity, TModel> modelMapper) {
        entity.setUpdateAt(new Date());
        var model = modelMapper.toJpaModel(entity);
        var newModel = repository.save(model);
        return modelMapper.toDomainModel(newModel);
    }

    /**
     * Retrieves an entity from the repository based on its unique identifier.
     *
     * @param id The unique identifier of the entity to be retrieved.
     * @param repository The JPA repository for the entity.
     * @param modelMapper The mapper for converting between domain and JPA entities.
     * @return The retrieved entity, or {@code null} if not found.
     */
    protected TEntity getById(UUID id, JpaRepository<TModel, UUID> repository, BaseJpaMapper<TEntity, TModel> modelMapper) {
        var model = repository.findById(id);
        return model.map(modelMapper::toDomainModel).orElse(null);

    }

    /**
     * Counts the total number of entities in the repository.
     *
     * @param repository The JPA repository for the entity.
     * @return The total number of entities.
     */
    protected long count(JpaRepository<TModel, UUID> repository) {
        return repository.count();
    }
    /**
     * Retrieves all entities from the repository based on specified criteria.
     *
     * @param criteria The criteria to filter entities.
     * @param repository The JPA specification executor for the entity.
     * @param specificationBuilder The builder for creating JPA specifications.
     * @param modelMapper The mapper for converting between domain and JPA entities.
     * @return A response containing a list of entities that match the criteria.
     */
    protected GetEntitiesResponse<TEntity> getAll(
            Criteria criteria,
            JpaSpecificationExecutor<TModel> repository,
            JpaSpecificationBuilder<TModel> specificationBuilder,
            BaseJpaMapper<TEntity, TModel> modelMapper
    ) {

        return GetEntitiesResponse.<TEntity>builder()
                .entities(toListOrderedPagedValues(criteria, repository, specificationBuilder, modelMapper).getData())
                .build();
    }

    /**
     * Converts a JPA specification executor and criteria into a paginated list of ordered entities.
     *
     * @param criteria The criteria to filter entities.
     * @param repository The JPA specification executor for the entity.
     * @param specificationBuilder The builder for creating JPA specifications.
     * @param modelMapper The mapper for converting between domain and JPA entities.
     * @return A paginated response containing a list of entities that match the criteria.
     */
    private PaginationResponse<TEntity> toListOrderedPagedValues(
            Criteria criteria,
            JpaSpecificationExecutor<TModel> repository,
            JpaSpecificationBuilder<TModel> specificationBuilder,
            BaseJpaMapper<TEntity, TModel> modelMapper
    ) {
        var orders = criteria.getOrders();
        var page = criteria.getPage();
        var pageSize = criteria.getPageSize();

        var specification = specificationBuilder.createSpecification(criteria);

        // Get the total amount of entities
        var totalAmount = repository.count(specification);

        // If there is no entities return empty list of entities.
        if (totalAmount == 0) {
            return PaginationResponse.<TEntity>builder()
                    .total(0)
                    .page(0)
                    .size(0)
                    .data(List.of())
                    .build();
        }

        // valid max page size by total amount.
        if (totalAmount < criteria.getPageSize()) {
            criteria.setPageSize((int) totalAmount);
        }

        // Verify if page is correct. The first correct page is 0
        if (criteria.getPage() != 0) {
            var rest = totalAmount % criteria.getPageSize() == 0 ? 0 : 1;
            var lastPage = totalAmount / criteria.getPageSize() + rest;
            if (criteria.getPage() >= lastPage) {
                return PaginationResponse.<TEntity>builder()
                        .page(criteria.getPage())
                        .size(criteria.getPageSize())
                        .total((int) totalAmount)
                        .data(List.of())
                        .build();
            }
        }

        // Get entity list by criteria
        Page<TModel> pageModelResponse;
        if (orders.isEmpty()) {
            pageModelResponse = repository.findAll(specification, PageRequest.of(page, pageSize));
        } else {
            List<String> orderAscendingProperties = new ArrayList<>();
            List<String> orderDescendingProperties = new ArrayList<>();
            orders.forEach(order -> {
                if (order.getOrderType() == OrderType.ASC) {
                    orderAscendingProperties.add(order.getFieldName());
                } else {
                    orderDescendingProperties.add(order.getFieldName());
                }
            });

            if (!orderAscendingProperties.isEmpty() && !orderDescendingProperties.isEmpty()) {
                pageModelResponse = repository.findAll(specification, PageRequest.of(page, pageSize,
                        Sort.by(Sort.Direction.ASC, String.join(",", orderAscendingProperties)).and(
                                Sort.by(Sort.Direction.DESC, String.join(",", orderDescendingProperties))
                        )));
            } else if (!orderAscendingProperties.isEmpty()) {
                pageModelResponse = repository.findAll(specification, PageRequest.of(page, pageSize,
                        Sort.by(Sort.Direction.ASC, String.join(",", orderAscendingProperties))));
            } else {
                pageModelResponse = repository.findAll(specification, PageRequest.of(page, pageSize,
                        Sort.by(Sort.Direction.DESC, String.join(",", orderDescendingProperties))));
            }
        }

        return PaginationResponse.<TEntity>builder()
                .page(criteria.getPage())
                .size(criteria.getPageSize())
                .total((int) totalAmount)
                .data(modelMapper.toDomainModel(pageModelResponse.toList()))
                .build();
    }
}
