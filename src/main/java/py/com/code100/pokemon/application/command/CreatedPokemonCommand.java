package py.com.code100.pokemon.application.command;

import py.com.code100.pokemon.domain.entities.Pokemon;

public interface CreatedPokemonCommand {

    void execute(Pokemon request);
}
