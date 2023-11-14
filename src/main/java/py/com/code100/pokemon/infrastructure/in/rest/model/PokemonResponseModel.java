package py.com.code100.pokemon.infrastructure.in.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Value;
import py.com.code100.pokemon.domain.entities.Pokemon;

@Value
@Builder
public class PokemonResponseModel {

    @JsonProperty("nombre")
    @NotBlank(message = "Nombre es requerido")
    String nombre;

    @JsonProperty("descripcion")
    @NotBlank(message = "Nombre es requerido")
    String descripcion;

    public static PokemonResponseModel of(Pokemon domain) {
        return PokemonResponseModel.builder()
                .nombre(domain.getNombre())
                .descripcion(domain.getDescripcion())
                .build();
    }
}
