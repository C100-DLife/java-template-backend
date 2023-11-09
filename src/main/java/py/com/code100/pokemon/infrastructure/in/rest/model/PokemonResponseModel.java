package py.com.code100.pokemon.infrastructure.rest.model;

import lombok.Builder;
import lombok.Value;
import py.com.code100.pokemon.domain.entities.Pokemon;

@Value
@Builder
public class PokemonResponseModel {

    String name;
    String description;

    public static PokemonResponseModel of(Pokemon domain) {
        return PokemonResponseModel.builder()
                .name(domain.getName())
                .description(domain.getDescription())
                .build();
    }
}
