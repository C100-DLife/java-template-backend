package py.com.code100.pokemon.infrastructure.in.rest.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import py.com.code100.pokemon.domain.entities.Pokemon;

@Value
@Builder
public class PokemonRequestModel {

    @NotBlank
    @Size(min = 3, message = "")
    String nombre;

    @NotBlank
    String descripcion;

    public Pokemon toDomain() {
        return Pokemon.builder()
                .nombre(this.nombre)
                .descripcion(this.descripcion)
                .build();
    }
}
