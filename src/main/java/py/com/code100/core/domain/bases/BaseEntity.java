package py.com.code100.core.domain.bases;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
@Builder
public class BaseEntity {

    protected UUID id;
    protected boolean isActive;
    protected Date createAt;
    protected Date updateAt;
    protected Date deleteAt;
}
