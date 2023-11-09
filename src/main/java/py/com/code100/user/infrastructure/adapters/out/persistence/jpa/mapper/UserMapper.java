package py.com.code100.user.infrastructure.adapters.out.persistence.jpa.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import py.com.code100.core.data.BaseJpaMapper;
import py.com.code100.user.domain.entities.User;
import py.com.code100.user.infrastructure.adapters.out.persistence.jpa.model.UserModelJPA;


@Mapper(componentModel = "spring")
public interface UserMapper extends BaseJpaMapper<User, UserModelJPA> {

}

