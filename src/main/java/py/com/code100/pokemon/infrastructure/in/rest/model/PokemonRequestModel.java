package py.com.code100.pokemon.infrastructure.in.rest.model;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import py.com.code100.pokemon.domain.entities.Pokemon;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PokemonRequestModel {

    @NotBlank
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
