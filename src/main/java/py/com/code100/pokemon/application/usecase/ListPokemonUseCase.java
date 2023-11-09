package py.com.code100.pokemon.application.usecase;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import py.com.code100.core.models.PaginationResponse;
import py.com.code100.pokemon.application.query.ListPokemonQuery;
import py.com.code100.pokemon.domain.entities.Pokemon;

import java.util.List;

@Component
@AllArgsConstructor
@Slf4j(topic = "ListPokemonUseCase")
public class ListPokemonUseCase implements ListPokemonQuery {

    @Override
    public PaginationResponse<Pokemon> execute(List<String> filters, List<String> orders, Integer page, Integer pageSize) {
        log.info("Ejecutando listar pokemon");

        return null;
    }
}
