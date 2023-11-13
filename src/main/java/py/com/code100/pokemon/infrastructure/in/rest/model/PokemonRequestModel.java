package py.com.code100.pokemon.infrastructure.in.rest.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Value;
import py.com.code100.pokemon.domain.entities.Pokemon;

@Value
@Builder
public class PokemonRequestModel {

    @NotBlank
    String name;

    @NotBlank
    String description;

    public Pokemon toDomain() {
        return Pokemon.builder()
                .name(this.name)
                .description(this.description)
                .build();
    }
}
