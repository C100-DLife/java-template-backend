package py.com.code100.core.middlewares;

import an.awesome.pipelinr.Command;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;
import py.com.code100.core.configurations.CommandValidator;

@Component
public class CommandValidationMiddleware implements Command.Middleware {
    private final ObjectProvider<CommandValidator> validators;

    CommandValidationMiddleware(ObjectProvider<CommandValidator> validators) {
        this.validators = validators;
    }

    @Override
    public <R, C extends Command<R>> R invoke(C command, Next<R> next) {
        validators.stream().filter(v -> v.matches(command)).findFirst().ifPresent(v -> {
            try {
                v.validate(command);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        return next.invoke();
    }
}
