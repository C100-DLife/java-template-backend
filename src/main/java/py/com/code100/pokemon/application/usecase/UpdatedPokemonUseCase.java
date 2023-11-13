package py.com.code100.pokemon.application.usecase;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import py.com.code100.core.annotations.UseCase;
import py.com.code100.core.config.errors.DomainException;
import py.com.code100.pokemon.application.command.UpdatedPokemonCommand;
import py.com.code100.pokemon.domain.entities.Pokemon;
import py.com.code100.pokemon.domain.exception.PokemonErrors;
import py.com.code100.pokemon.domain.repositories.PokemonRepository;

import java.util.Objects;
import java.util.UUID;

@UseCase
@AllArgsConstructor
@Slf4j(topic = "UpdatedPokemonUseCase")
public class UpdatedPokemonUseCase implements UpdatedPokemonCommand {

    private final PokemonRepository pokemonRepository;

    @Override
    public Pokemon execute(UUID id, Pokemon request) {
        log.info("Ejecutando actualizar pokemon: {}", id);

        log.info("Obteniendo la información pokemon: {}", id);
        var response = pokemonRepository.getById(id);
        if (Objects.isNull(response)) {
            log.error("El pokemon {} no está registrado", id);
            throw new DomainException(PokemonErrors.POKEMON_100);
        }

        response = pokemonRepository.update(request);
        log.info("Información del pokemon {} actualizada correctamente", id);

        return response;
    }
}
