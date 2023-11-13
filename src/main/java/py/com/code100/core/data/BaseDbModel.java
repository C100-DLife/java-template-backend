package py.com.code100.core.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
@SuperBuilder
@Component
@AllArgsConstructor
@RequiredArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class BaseDbModel {

    @Id
    @UuidGenerator(style = UuidGenerator.Style.AUTO)
    @GeneratedValue
    protected UUID id;

    @Column(nullable = false)
    protected boolean isActive;

    @CreationTimestamp
    @Column(name = "created_at", insertable = false, updatable = false, nullable = false)
    protected Date createAt;

    @UpdateTimestamp
    @Column(name = "updated_at", insertable = false)
    protected Date updateAt;

    @Column(name = "deleted_at")
    protected Date deleteAt;
}
