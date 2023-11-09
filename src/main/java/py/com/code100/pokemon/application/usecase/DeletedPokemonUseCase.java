package py.com.code100.pokemon.application.usecase;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import py.com.code100.pokemon.application.command.DeletedPokemonCommand;
import py.com.code100.pokemon.domain.repositories.PokemonRepository;

import java.util.UUID;

@Component
@AllArgsConstructor
@Slf4j(topic = "DeletedPokemonUseCase")
public class DeletedPokemonUseCase implements DeletedPokemonCommand {

    private final PokemonRepository repository;

    @Override
    public void execute(UUID id) {
        log.info("Ejecutando eliminación de pokemon: {}", id);

        repository.delete(id);
    }
}
