package py.com.code100.user.infrastructure.adapters.in.web;

import an.awesome.pipelinr.Pipeline;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import py.com.code100.core.annotations.WebAdapter;
import py.com.code100.user.application.commands.CreateUserCommand;

@WebAdapter
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class CreateUserController {

    private final Pipeline mediator;

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody CreateUserCommand command) {

        var response = mediator.send(command);
        if (!response.isSuccess()) {
            return new ResponseEntity<>(response.getErrorResponse(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(response.getValue(), HttpStatus.CREATED);
    }
}
