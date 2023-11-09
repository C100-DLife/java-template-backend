package py.com.code100.core.middlewares;

import an.awesome.pipelinr.Command;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TransactionalMiddleware implements Command.Middleware {

    @Override
    public <R, C extends Command<R>> R invoke(C command, Next<R> next) {
        System.out.println("command send. " + command.getClass().getSimpleName() + " Start process: " + new Date());
        R response = next.invoke();
        System.out.println("command response. End process: " + new Date());
        return response;
    }
}
