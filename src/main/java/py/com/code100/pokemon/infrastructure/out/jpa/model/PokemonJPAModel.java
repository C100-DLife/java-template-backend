package py.com.code100.pokemon.infrastructure.out.jpa.model;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import py.com.code100.core.data.BaseDbModel;

@Data
@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pokemon")
public class PokemonJPAModel extends BaseDbModel {

    @Basic(optional = false)
    @Column(name = "nombre", length = 200, unique = true, nullable = false)
    private String name;

    @Basic(optional = false)
    @Column(name = "descripcion", length = 500, unique = true, nullable = false)
    private String decription;
}
