package py.com.code100.pokemon.infrastructure.in.rest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
public class RestResponse<T> {
    Integer status;
    T result;
}
