package py.com.code100.core.config.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponseException;
import py.com.code100.core.config.web.MessageLocaleSingleton;

import java.net.URI;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Custom exception to represent domain-related issues in the application.
 */
public class DomainException extends ErrorResponseException {

    /**
     * Constructs a new domain exception with the provided error code.
     *
     * @param errorCode The error code identifying the domain exception.
     */
    public DomainException(ErrorCode errorCode) {
        super(HttpStatus.CONFLICT, asProblemDetail(errorCode), null);
    }

    /**
     * Converts an error code into a standard problem detail.
     *
     * @param errorCode The error code identifying the domain exception.
     * @return A standard problem detail associated with the error code.
     */
    private static ProblemDetail asProblemDetail(ErrorCode errorCode) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.CONFLICT);
        problemDetail.setTitle("Domain Exception");
        problemDetail.setProperty("errorCategory", ErrorType.GENERIC);
        problemDetail.setType(URI.create("https://developer.mozilla.org/en-US/docs/Web/HTTP/Status/409"));
        problemDetail.setProperty("errorCode", errorCode.getCode());
        problemDetail.setDetail(MessageLocaleSingleton.getMessageService().getMessage(errorCode.getCode()));
        problemDetail.setProperty("timestamp", formatDate(Instant.now()));
        return problemDetail;
    }

    /**
     * Formats an Instant instance into a date and time string with the pattern "dd/MM/yyyy HH:mm:ss".
     *
     * @param now The Instant instance to format.
     * @return The formatted string representing the date and time.
     */
    static String formatDate(Instant now) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        ZoneId zonaHoraria = ZoneId.systemDefault();
        Date date = Date.from(now);
        return formatter.format(date.toInstant().atZone(zonaHoraria));
    }
}
