package py.com.code100.pokemon.application.query;

import py.com.code100.pokemon.domain.entities.Pokemon;

import java.util.UUID;

public interface FindPokemonQuery {

    Pokemon execute(UUID id);
}
