package py.com.code100.user.application.query.getUser;

import an.awesome.pipelinr.Command;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import py.com.code100.core.domain.responses.ProcessResponse;
import py.com.code100.core.domain.responses.Response;
import py.com.code100.user.application.query.responses.GetUserQueryResponse;
import py.com.code100.user.application.query.responses.GetUserQueryResponseMapper;
import py.com.code100.user.domain.repository.UserRepository;

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

        var response = Mappers.getMapper(GetUserQueryResponseMapper.class).toDto(entity);
        return processResponse.success(response);
    }
}
