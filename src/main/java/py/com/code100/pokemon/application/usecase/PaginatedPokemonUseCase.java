package py.com.code100.pokemon.application.usecase;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import py.com.code100.core.annotations.UseCase;
import py.com.code100.core.models.PaginationResponse;
import py.com.code100.core.specification.Criteria;
import py.com.code100.pokemon.application.query.PaginatedPokemonQuery;
import py.com.code100.pokemon.domain.entities.Pokemon;
import py.com.code100.pokemon.domain.repositories.PokemonRepository;

import java.util.List;

@UseCase
@AllArgsConstructor
@Slf4j(topic = "ListPokemonUseCase")
public class PaginatedPokemonUseCase implements PaginatedPokemonQuery {

    private final PokemonRepository pokemonRepository;

    @Override
    public PaginationResponse<Pokemon> execute(List<String> filters, List<String> orders, Integer page, Integer size) {
        log.info("Ejecutando listar pokemon");

        var response = pokemonRepository.getAll(new Criteria());

        return PaginationResponse.<Pokemon>builder()
                .page(page)
                .size(size)
                .total(response.getEntities().size())
                .data(response.getEntities())
                .build();
    }
}
