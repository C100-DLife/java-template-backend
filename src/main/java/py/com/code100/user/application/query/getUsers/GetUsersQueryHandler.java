package py.com.code100.user.application.query.getUsers;

import an.awesome.pipelinr.Command;
import org.springframework.stereotype.Component;
import py.com.code100.core.domain.responses.ProcessResponse;
import py.com.code100.core.domain.responses.Response;
import py.com.code100.core.specification.Criteria;
import py.com.code100.user.application.query.responses.GetUsersQueryResponse;
import py.com.code100.user.domain.repository.UserRepository;

@Component
public class GetUsersQueryHandler implements Command.Handler<GetUsersQuery, Response<GetUsersQueryResponse>> {
    private final UserRepository repository;

    public GetUsersQueryHandler(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Response<GetUsersQueryResponse> handle(GetUsersQuery query) {

        var processResponse = new ProcessResponse<GetUsersQueryResponse>();

        Criteria criteria;
        try {
            criteria = new Criteria(query.getFilterPagination());
        } catch (Exception e) {
            return processResponse.error(e);
        }

        var entitiesResponse = repository.getAll(criteria);
        var response = new GetUsersQueryResponse(entitiesResponse.getEntities(), entitiesResponse.getPagination());

        return processResponse.success(response);
    }
}
