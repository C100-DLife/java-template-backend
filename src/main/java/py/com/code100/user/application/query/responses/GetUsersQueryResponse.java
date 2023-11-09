package py.com.code100.user.application.query.responses;

import org.mapstruct.factory.Mappers;
import py.com.code100.core.models.GetEntitiesResponse;
import py.com.code100.core.models.PaginationResponse;
import py.com.code100.user.domain.entities.User;

import java.util.List;

public class GetUsersQueryResponse extends GetEntitiesResponse<GetUserQueryResponse> {
    public GetUsersQueryResponse(List<User> entities, PaginationResponse pagination) {
        super(Mappers.getMapper(GetUserQueryResponseMapper.class).toDtoList(entities), pagination);
    }
}
