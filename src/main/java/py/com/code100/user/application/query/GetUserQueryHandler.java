package py.com.code100.user.application.query;

import an.awesome.pipelinr.Command;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import py.com.code100.core.domain.responses.ProcessResponse;
import py.com.code100.core.domain.responses.Response;
import py.com.code100.user.domain.repository.UserRepository;
import py.com.code100.user.infrastructure.adapters.out.persistence.jpa.mapper.UserMapper;

@Component
public class GetUserQueryHandler implements Command.Handler<GetUserQuery, Response<GetUserQueryResponse>> {

    private final UserRepository repository;

    public GetUserQueryHandler(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Response<GetUserQueryResponse> handle(GetUserQuery query) {
        var processResponse = new ProcessResponse<GetUserQueryResponse>();
        var entity = repository.getById(query.getId());
        GetUserQueryResponse response = GetUserQueryResponse.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .apellido(entity.getApellido())
                .email(entity.getEmail())
                .edad(entity.getEdad())
                .build();
        return processResponse.success(response);
    }
}
