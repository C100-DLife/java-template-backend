package py.com.code100.pokemon.application.query;

import py.com.code100.core.models.PaginationResponse;
import py.com.code100.pokemon.domain.entities.Pokemon;

import java.util.List;

public interface PaginatedPokemonQuery {

    PaginationResponse<Pokemon> execute(List<String> filters, List<String> orders, Integer page, Integer pageSize);
}
