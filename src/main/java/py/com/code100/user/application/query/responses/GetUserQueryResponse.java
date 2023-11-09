package py.com.code100.user.application.query.responses;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class GetUserQueryResponse {
    private UUID id;
    private String nombre;
    private String apellido;
    private String email;
    private int edad;
}
