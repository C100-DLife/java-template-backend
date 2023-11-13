package py.com.code100.pokemon.application.usecase;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import py.com.code100.core.annotations.UseCase;
import py.com.code100.core.config.errors.DomainException;
import py.com.code100.pokemon.application.query.FindPokemonQuery;
import py.com.code100.pokemon.domain.entities.Pokemon;
import py.com.code100.pokemon.domain.exception.PokemonErrors;
import py.com.code100.pokemon.domain.repositories.PokemonRepository;

import java.util.UUID;

@UseCase
@AllArgsConstructor
@Slf4j(topic = "FindPokemonUseCase")
public class FindPokemonUseCase implements FindPokemonQuery {

    private final PokemonRepository pokemonRepository;

    @Override
    public Pokemon execute(UUID id) {
        log.info("Ejecutando servicio de buscar pokemon: {}", id);

        var response = pokemonRepository.getById(id);
        if (response == null) {
            log.error("El pokemon {} no está registrado", id);
            throw new DomainException(PokemonErrors.POKEMON_100);
        }

        log.debug("Respuesta del servicio buscar pokemon {}: data: [{}]", id, response);
        return response;
    }
}
