package py.com.code100.pokemon.application.usecase;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import py.com.code100.pokemon.application.command.UpdatedPokemonCommand;
import py.com.code100.pokemon.domain.entities.Pokemon;

import java.util.UUID;

@Component
@AllArgsConstructor
@Slf4j(topic = "UpdatedPokemonUseCase")
public class UpdatedPokemonUseCase implements UpdatedPokemonCommand {

    @Override
    public Pokemon execute(UUID id, Pokemon request) {
        log.info("Ejecutando actualizar pokemon: {}", id);

        return null;
    }
}
