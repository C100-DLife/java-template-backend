package py.com.code100.pokemon.infrastructure.in.runner;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import py.com.code100.pokemon.domain.entities.Pokemon;
import py.com.code100.pokemon.domain.repositories.PokemonRepository;

@Component
@AllArgsConstructor
@Slf4j(topic = "PokemonLoader")
public class PokemonLoader implements CommandLineRunner {

    private final PokemonRepository pokemonRepository;

    @Override
    public void run(String... args) {

        try {
            if (pokemonRepository.count() == 0) {
                var zoroark = Pokemon.builder()
                        .name("Zoroark GX")
                        .description("Tras su lanzamiento en la expansión Shining Legends de 2017, ningún mazo Pokémon estaba completo sin Zoroark GX . Dominaba el meta tan rápido como podía noquear al Pokémon de tu oponente. Y para ser claros, eso fue muy rápido. Su ataque Riotous Beating infligió 20 daños por cada uno de tus Pokémon en juego, mientras que su habilidad Intercambiar te permitía robar dos cartas después de descartar una. Combinados, puedes reforzar a Zoroark con cartas clave de entrenador Pokémon y aplastar a las criaturas de tu oponente de un solo golpe")
                        .build();
                pokemonRepository.add(zoroark);
            }
        } catch (Exception e) {
            log.error("Error comando PokemonLoader error: {}", e.getMessage());
            throw e;
        }
    }
}
