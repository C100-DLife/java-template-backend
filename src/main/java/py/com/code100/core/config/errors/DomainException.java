package py.com.code100.core.config.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponseException;

import java.net.URI;
import java.time.Instant;

public class DomainException extends ErrorResponseException {

    public DomainException(ErrorCode errorCode) {
        super(HttpStatus.CONFLICT, asProblemDetail(errorCode), null);
    }

    private static ProblemDetail asProblemDetail(ErrorCode errorCode) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.CONFLICT);
        problemDetail.setTitle("Domain Exception");
        problemDetail.setProperty("errorCategory", ErrorType.GENERIC);
        problemDetail.setType(URI.create("https://api.bookmarks.com/errors/not-found"));
        problemDetail.setProperty("errorCode", errorCode.getCode());
        problemDetail.setDetail(errorCode.getMessage());
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
    }
}
