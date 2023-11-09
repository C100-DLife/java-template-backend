package py.com.code100.user.infrastructure.adapters.in.web;

import an.awesome.pipelinr.Pipeline;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import py.com.code100.core.annotations.WebAdapter;
import py.com.code100.core.models.FilterPaginationQueryModel;
import py.com.code100.user.application.query.getUsers.GetUsersQuery;

import java.util.List;
@WebAdapter
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class GetAllUsersController {

    private final Pipeline mediator;
    @GetMapping
    public ResponseEntity<Object> get(@RequestParam(value = "filters", required = false) List<String> filters,
                                      @RequestParam(value = "orders", required = false) List<String> orders,
                                      @RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                      @RequestParam(value = "pageSize", required = false, defaultValue = "0") int pageSize) {

        var filterPagination = new FilterPaginationQueryModel(filters, orders, page, pageSize);
        var response = mediator.send(new GetUsersQuery(filterPagination));

        if (!response.isSuccess()) {
            return new ResponseEntity<>(response.getErrorResponse(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(response.getValue(), HttpStatus.OK);
    }
}
