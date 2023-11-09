package py.com.code100.core.data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
@Builder
@SuperBuilder
@Component
@AllArgsConstructor
@RequiredArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class BaseDbModel {

    @Id
    @UuidGenerator(style = UuidGenerator.Style.AUTO)
    @GeneratedValue
    protected UUID id;

    protected boolean isActive;

    protected Date createAt;

    protected Date updateAt;

    protected Date deleteAt;
}
