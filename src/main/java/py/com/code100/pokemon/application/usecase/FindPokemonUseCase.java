package py.com.code100.pokemon.application.usecase;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import py.com.code100.pokemon.application.query.FindPokemonQuery;
import py.com.code100.pokemon.domain.entities.Pokemon;

import java.util.UUID;

@Component
@AllArgsConstructor
@Slf4j(topic = "FindPokemonUseCase")
public class FindPokemonUseCase implements FindPokemonQuery {

    @Override
    public Pokemon execute(UUID id) {
        log.info("Ejecutando buscar pokemon: {}", id);

        return null;
    }
}
