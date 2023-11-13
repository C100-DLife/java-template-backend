package py.com.code100.pokemon.application.usecase;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import py.com.code100.core.annotations.UseCase;
import py.com.code100.core.config.ErrorCode;
import py.com.code100.pokemon.application.command.DeletedPokemonCommand;
import py.com.code100.pokemon.domain.exception.PokemonDeletedException;
import py.com.code100.pokemon.domain.exception.PokemonNotFoundException;
import py.com.code100.pokemon.domain.repositories.PokemonRepository;

import java.util.Objects;
import java.util.UUID;

@UseCase
@AllArgsConstructor
@Slf4j(topic = "DeletedPokemonUseCase")
public class DeletedPokemonUseCase implements DeletedPokemonCommand {

    private final PokemonRepository pokemonRepository;

    @Override
    public void execute(UUID id) {
        log.info("Ejecutando servicio de eliminación de pokemon: {}", id);

        log.info("Obteniendo la información pokemon: {}", id);
        var response = pokemonRepository.getById(id);
        if (Objects.isNull(response)) {
            log.error("El pokemon {} no está registrado", id);
            throw new PokemonNotFoundException(ErrorCode.POKEMON_NOT_FOUND);
        }
        if (Objects.nonNull(response.getDeleteAt())) {
            log.error("El pokemon {} ya se encuentra eliminado", id);
            throw new PokemonDeletedException(ErrorCode.POKEMON_NOT_FOUND);
        }

        pokemonRepository.delete(id);
        log.info("Pokemon eliminado correctamente: {}", id);
    }
}
