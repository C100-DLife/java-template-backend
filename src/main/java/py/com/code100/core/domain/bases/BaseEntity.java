package py.com.code100.core.domain.bases;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.Date;
import java.util.UUID;

@Data
@SuperBuilder
public class BaseEntity {

    protected UUID id;
    protected boolean isActive;
    protected Date createAt;
    protected Date updateAt;
    protected Date deleteAt;
}
