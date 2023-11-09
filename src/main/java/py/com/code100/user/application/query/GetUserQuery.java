package py.com.code100.user.application.query;

import an.awesome.pipelinr.Command;
import py.com.code100.core.domain.responses.Response;

import java.util.UUID;

public class GetUserQuery implements Command<Response<GetUserQueryResponse>> {
    private UUID id;

    public GetUserQuery() {
    }

    public GetUserQuery(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
