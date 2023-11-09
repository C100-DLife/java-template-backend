package py.com.code100.core.configurations;

import an.awesome.pipelinr.Command;
import com.google.common.reflect.TypeToken;
import org.springframework.stereotype.Component;

@Component
public interface CommandValidator<C extends Command<R>, R> {
    void validate(C command);

    default boolean matches(C command) {
        TypeToken<C> typeToken = new TypeToken<>(getClass()) { // available in Guava 12+.
        };

        return typeToken.isSupertypeOf(command.getClass());
    }
}