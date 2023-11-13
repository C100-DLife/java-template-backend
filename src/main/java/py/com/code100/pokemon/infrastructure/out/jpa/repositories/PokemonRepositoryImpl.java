package py.com.code100.pokemon.infrastructure.out.jpa.repositories;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import py.com.code100.core.domain.bases.BaseRepositoryImpl;
import py.com.code100.core.models.GetEntitiesResponse;
import py.com.code100.core.specification.Criteria;
import py.com.code100.core.specification.JpaSpecificationBuilder;
import py.com.code100.pokemon.domain.entities.Pokemon;
import py.com.code100.pokemon.domain.repositories.PokemonRepository;
import py.com.code100.pokemon.infrastructure.out.jpa.mapper.PokemonMapper;
import py.com.code100.pokemon.infrastructure.out.jpa.model.PokemonJPAModel;

import java.util.UUID;


@AllArgsConstructor
@Component
public class PokemonRepositoryImpl extends BaseRepositoryImpl<Pokemon, PokemonJPAModel> implements PokemonRepository {

    private final PokemonJPARepository jpaRepository;
    private final PokemonMapper mapper;
    private final JpaSpecificationBuilder<PokemonJPAModel> specificationBuilder;

    @Override
    public Pokemon add(Pokemon entity) {
        return add(entity, jpaRepository, mapper);
    }

    @Override
    public void delete(UUID id) {
        delete(id, jpaRepository);
    }

    @Override
    public Pokemon update(Pokemon entity) {
        return update(entity, jpaRepository, mapper);
    }

    @Override
    public Pokemon getById(UUID id) {
        return getById(id, jpaRepository, mapper);
    }

    @Override
    public long count() {
        return count(jpaRepository);
    }

    @Override
    public GetEntitiesResponse<Pokemon> getAll(Criteria criteria) {
        return getAll(criteria, jpaRepository, specificationBuilder, mapper);
    }
}
