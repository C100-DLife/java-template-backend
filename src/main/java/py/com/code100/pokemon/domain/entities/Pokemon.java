package py.com.code100.pokemon.domain.entities;

import lombok.Builder;
import lombok.Value;
import py.com.code100.core.domain.bases.BaseEntity;

@Value
@Builder
public class Pokemon extends BaseEntity {

    String name;
    String description;
}
