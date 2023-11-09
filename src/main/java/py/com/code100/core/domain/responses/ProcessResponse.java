package py.com.code100.core.domain.responses;

public class ProcessResponse<T> {

    public Response<T> success(T value)    {
        return new Response<>(value);
    }

    public Response<T> error(String message){
        ErrorResponse errorResponse = new ErrorResponse(message);
        return new Response<>(errorResponse);
    }

    public Response<T> error(ErrorResponse errorResponse){
        return new Response<>(errorResponse);
    }

    public Response<T> error(Exception exception){
        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage());
        return error(errorResponse);
    }
}