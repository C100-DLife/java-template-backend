package py.com.code100.pokemon.application.usecase;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import py.com.code100.core.annotations.UseCase;
import py.com.code100.pokemon.application.command.CreatedPokemonCommand;
import py.com.code100.pokemon.domain.entities.Pokemon;
import py.com.code100.pokemon.domain.repositories.PokemonRepository;

@UseCase
@AllArgsConstructor
@Slf4j(topic = "CreatedPokemonUseCase")
public class CreatedPokemonUseCase implements CreatedPokemonCommand {

    private final PokemonRepository pokemonRepository;

    @Override
    public void execute(Pokemon request) {
        log.info("Ejecutando servicio de creacion de pokemon");

        var response = pokemonRepository.add(request);
        log.info("Pokemon creado correctamente: {}", response.getId());
    }
}
