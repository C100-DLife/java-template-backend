package py.com.code100.core.models;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder(toBuilder = true)
public class PaginationResponse<T> {

    Integer page;
    Integer size;
    Integer total;
    List<T> data;
}
