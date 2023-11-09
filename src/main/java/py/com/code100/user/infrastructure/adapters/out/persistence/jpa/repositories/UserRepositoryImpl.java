package py.com.code100.user.infrastructure.adapters.out.persistence.jpa.repositories;

import org.springframework.stereotype.Component;
import py.com.code100.core.domain.bases.BaseRepositoryImpl;
import py.com.code100.core.models.GetEntitiesResponse;
import py.com.code100.core.specification.Criteria;
import py.com.code100.core.specification.JpaSpecificationBuilder;
import py.com.code100.user.domain.entities.User;
import py.com.code100.user.domain.repository.UserRepository;
import py.com.code100.user.infrastructure.adapters.out.persistence.jpa.mapper.UserMapper;
import py.com.code100.user.infrastructure.adapters.out.persistence.jpa.model.UserModelJPA;

import java.util.UUID;

@Component
public class UserRepositoryImpl extends BaseRepositoryImpl<User, UserModelJPA> implements UserRepository {

    private final UserJpaRepository repository;
    private final UserMapper modelMapper;
    private final JpaSpecificationBuilder<UserModelJPA> specificationBuilder;

    public UserRepositoryImpl(UserJpaRepository repository,
                              JpaSpecificationBuilder<UserModelJPA> specificationBuilder,
                              UserMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
        this.specificationBuilder = specificationBuilder;
    }

    @Override
    public User add(User entity) {
        return add(entity, repository, modelMapper);
    }

    @Override
    public void delete(UUID id) {
        delete(id, repository);
    }

    @Override
    public User update(User entity) {
        return update(entity, repository, modelMapper);
    }

    @Override
    public User getById(UUID id) {
        return getById(id, repository, modelMapper);
    }

    @Override
    public long count() {
        return count(repository);
    }

    @Override
    public GetEntitiesResponse<User> getAll(Criteria criteria) {
        return getAll(criteria, repository, specificationBuilder, modelMapper);
    }
}
