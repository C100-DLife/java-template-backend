package py.com.code100.user.application.commands;

import an.awesome.pipelinr.Command;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import py.com.code100.core.domain.responses.Response;

@Data
public class CreateUserCommand implements Command<Response<CreateUserCommandResponse>> {

    @NotBlank String nombre;
    @NotBlank String apellido;
    @Email String email;
    @Min(18)@Max(80) int edad;
}
