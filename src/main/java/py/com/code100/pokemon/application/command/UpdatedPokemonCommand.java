package py.com.code100.pokemon.application.command;

import py.com.code100.pokemon.domain.entities.Pokemon;

import java.util.UUID;

public interface UpdatedPokemonCommand {

    Pokemon execute(UUID id, Pokemon request);
}
