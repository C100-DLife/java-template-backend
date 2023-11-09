package py.com.code100.user.application.query.getUsers;

import an.awesome.pipelinr.Command;
import org.springframework.stereotype.Component;
import py.com.code100.core.domain.responses.Response;
import py.com.code100.core.models.FilterPaginationQueryModel;
import py.com.code100.user.application.query.responses.GetUsersQueryResponse;

@Component
public class GetUsersQuery implements Command<Response<GetUsersQueryResponse>> {
    private FilterPaginationQueryModel filterPagination;

    public GetUsersQuery(FilterPaginationQueryModel filterPagination) {
        this.filterPagination = filterPagination;
    }

    public FilterPaginationQueryModel getFilterPagination() {
        return filterPagination;
    }

    public void setFilterPagination(FilterPaginationQueryModel filterPagination) {
        this.filterPagination = filterPagination;
    }

}
