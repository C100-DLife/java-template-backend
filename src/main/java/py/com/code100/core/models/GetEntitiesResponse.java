package py.com.code100.core.models;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class GetEntitiesResponse<T> {

    List<T> entities;
}
