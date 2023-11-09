package py.com.code100.user.infrastructure.adapters.out.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import py.com.code100.user.infrastructure.adapters.out.persistence.jpa.model.UserModelJPA;

import java.util.UUID;

@Repository
public interface UserJpaRepository extends JpaRepository<UserModelJPA, UUID>, JpaSpecificationExecutor<UserModelJPA> {
}
