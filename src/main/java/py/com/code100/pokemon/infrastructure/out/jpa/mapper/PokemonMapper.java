package py.com.code100.pokemon.infrastructure.out.jpa.mapper;

import org.mapstruct.Mapper;
import py.com.code100.core.data.BaseJpaMapper;
import py.com.code100.pokemon.domain.entities.Pokemon;
import py.com.code100.pokemon.infrastructure.out.jpa.model.PokemonJPAModel;

@Mapper(componentModel = "spring")
public interface PokemonMapper extends BaseJpaMapper<Pokemon, PokemonJPAModel> {
}
