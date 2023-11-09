package py.com.code100.user.application.commands;

import an.awesome.pipelinr.Command;
import an.awesome.pipelinr.Pipeline;
import org.springframework.stereotype.Component;
import py.com.code100.core.domain.responses.ProcessResponse;
import py.com.code100.core.domain.responses.Response;
import py.com.code100.user.domain.entities.User;
import py.com.code100.user.domain.repository.UserRepository;


@Component
public class CreateUserCommandHandler implements Command.Handler<CreateUserCommand, Response<CreateUserCommandResponse>> {

    private final UserRepository userRepository;
    private final Pipeline mediator;


    public CreateUserCommandHandler(UserRepository userRepository, Pipeline mediator) {
        this.userRepository = userRepository;
        this.mediator = mediator;
    }

    @Override
    public Response<CreateUserCommandResponse> handle(CreateUserCommand command) {
        var processResponse = new ProcessResponse<CreateUserCommandResponse>();

//        if (userRepository.existsByEmail(command.getEmail())) {
//            return processResponse.error("El email ya existe");
//        }
        var entity = new User(
                command.getNombre(),
                command.getApellido(),
                command.getEmail(),
                command.getEdad());
        entity = userRepository.add(entity);

        var response = new CreateUserCommandResponse(entity);
        return processResponse.success(response);
    }
}
