package py.com.code100.user.infrastructure.adapters.in.web;

import an.awesome.pipelinr.Pipeline;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import py.com.code100.core.annotations.WebAdapter;
import py.com.code100.user.application.query.getUser.GetUserQuery;

import java.util.UUID;

@WebAdapter
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class GetUserByIdController {

    private final Pipeline mediator;

    @GetMapping("/{id}")
    public ResponseEntity<Object> get(@PathVariable UUID id) {

        var query = new GetUserQuery(id);
        var response = mediator.send(query);
        if (!response.isSuccess()) {
            return new ResponseEntity<>(response.getErrorResponse(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(response.getValue(), HttpStatus.OK);
    }
}
