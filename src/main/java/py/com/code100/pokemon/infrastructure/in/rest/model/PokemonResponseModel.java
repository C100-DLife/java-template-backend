package py.com.code100.pokemon.infrastructure.in.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Value;
import py.com.code100.pokemon.domain.entities.Pokemon;

@Value
@Builder
public class PokemonResponseModel {

    @JsonProperty("name")
    @NotBlank(message = "Nombre es requerido")
    String name;

    @JsonProperty("description")
    @NotBlank(message = "Nombre es requerido")
    String description;

    public static PokemonResponseModel of(Pokemon domain) {
        return PokemonResponseModel.builder()
                .name(domain.getName())
                .description(domain.getDescription())
                .build();
    }
}
