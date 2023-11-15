package py.com.code100.pokemon.application.command;

import java.util.UUID;

public interface DeletedPokemonCommand {

    void execute(UUID id);
}
