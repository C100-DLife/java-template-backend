package py.com.code100.user.application.commands;

import lombok.Getter;
import py.com.code100.user.domain.entities.User;

import java.util.UUID;

@Getter
public class CreateUserCommandResponse {
    private UUID id;

    public CreateUserCommandResponse(User entity) {
        this.id = entity.getId();
    }

    public void setId(UUID id) {
        this.id = id;
    }
}

