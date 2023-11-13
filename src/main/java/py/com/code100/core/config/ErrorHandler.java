package py.com.code100.core.config;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import py.com.code100.core.config.exception.GenericException;
import py.com.code100.pokemon.domain.exception.PokemonNotFoundException;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

@ControllerAdvice
@Slf4j(topic = "ErrorHandler")
public class ErrorHandler {

    private static final String DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss[.SSSSSSSSS]['Z']";
    private static final String PROFILE_LOCAL = "local";
    private static final String PROFILE_DEVELOPMENT = "dev";

    private final HttpServletRequest httpServletRequest;

    @Value("${spring.profiles.active:}")
    private String activeProfile;

    public ErrorHandler(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    /**
     * Status 400
     *
     * @param exception Excepción
     * @return
     */
    @ExceptionHandler({
            MethodArgumentNotValidException.class,
            MissingRequestHeaderException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiErrorResponse> handler(Exception exception) {
        log.error(HttpStatus.BAD_REQUEST.getReasonPhrase(), exception);
        return buildREsponseError(HttpStatus.BAD_REQUEST, exception, ErrorCode.VALID_DATA_ERROR);
    }

    /**
     * Status 404
     * @param exception Excepción
     * @return
     */
    @ExceptionHandler({
            PokemonNotFoundException.class
    })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiErrorResponse> handler(GenericException exception) {
        log.error(HttpStatus.NOT_FOUND.getReasonPhrase(), exception);
        return buildREsponseError(HttpStatus.NOT_FOUND, exception, exception.getErrorCode());
    }

    /**
     * Status 500
     *
     * @param throwable Excepción
     * @return
     */
    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ApiErrorResponse> handler(Throwable throwable) {
        log.error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), throwable);
        return buildREsponseError(HttpStatus.INTERNAL_SERVER_ERROR, throwable, ErrorCode.INTERNAL_ERROR);
    }

    private ResponseEntity<ApiErrorResponse> buildREsponseError(HttpStatus httpStatus, Throwable throwable, ErrorCode errorCode) {

        final var debugMessage = shoulDebug() ? Arrays.toString(throwable.getStackTrace()) : "";

        final var queryString = Optional.ofNullable(httpServletRequest.getQueryString())
                .orElse("");


        var metaData = Map.of(
                "query_string", queryString,
                "stack_trace", debugMessage
        );

        final var apiErrorResponse = ApiErrorResponse.builder()
                .status(httpStatus.value())
                .timestamp(LocalDateTime.now())
                .code(errorCode.getValue())
                .resource(httpServletRequest.getRequestURI())
                .detail(shoulDebug() ? getDetailedErrorMessage(throwable) : "")
                .metadata(metaData)
                .build();

        return new ResponseEntity<>(apiErrorResponse, httpStatus);
    }

    private String getDetailedErrorMessage(Throwable throwable) {
        return String.format("%s: %s", throwable.getClass().getCanonicalName(), throwable.getMessage());
    }

    private boolean isLocal() {
        return Optional.of(activeProfile).filter(s -> s.contains(PROFILE_LOCAL)).isPresent();
    }

    private boolean isDevelopment() {
        return Optional.of(activeProfile).filter(s -> s.contains(PROFILE_DEVELOPMENT)).isPresent();
    }

    private boolean shoulDebug() {
        return isLocal() || isDevelopment();
    }

    @Builder
    private static class ApiErrorResponse {

        private final Integer status;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_PATTERN)
        private final LocalDateTime timestamp;

        private final Integer code;

        private final String resource;

        private final String detail;

        private final Map<String, String> metadata;
    }
}
