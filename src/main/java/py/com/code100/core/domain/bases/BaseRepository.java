package py.com.code100.core.domain.bases;

import py.com.code100.core.models.GetEntitiesResponse;
import py.com.code100.core.specification.Criteria;

import java.util.UUID;

public interface BaseRepository<TEntity extends BaseEntity> {
    TEntity add(TEntity entity);
    void delete(UUID id);
    TEntity update(TEntity entity);
    TEntity getById(UUID id);
    long count();
    GetEntitiesResponse<TEntity> getAll(Criteria criteria);
}
