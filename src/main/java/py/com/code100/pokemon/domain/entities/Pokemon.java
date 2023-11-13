package py.com.code100.pokemon.domain.entities;

import lombok.Value;
import lombok.experimental.SuperBuilder;
import py.com.code100.core.domain.bases.BaseEntity;

@Value
@SuperBuilder
public class Pokemon extends BaseEntity {

    String name;
    String description;
}
