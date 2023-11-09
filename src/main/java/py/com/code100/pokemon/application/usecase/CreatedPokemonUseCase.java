package py.com.code100.pokemon.application.usecase;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import py.com.code100.pokemon.application.command.CreatedPokemonCommand;
import py.com.code100.pokemon.domain.repositories.PokemonRepository;
import py.com.code100.pokemon.domain.entities.Pokemon;

@Component
@AllArgsConstructor
@Slf4j(topic = "CreatedPokemonUseCase")
public class CreatedPokemonUseCase implements CreatedPokemonCommand {

    private final PokemonRepository repository;

    @Override
    public void execute(Pokemon request) {
        log.info("Ejecutando servicio de creacion de pokemon");

        repository.add(request);
    }
}
