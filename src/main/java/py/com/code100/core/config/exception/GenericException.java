package py.com.code100.core.config.exception;

import py.com.code100.core.config.ErrorCode;

public abstract class GenericException extends RuntimeException {

    private static final String SPACE = " ";
    private static final String COMMA = ",";

    private final ErrorCode errorCode;

    protected GenericException(ErrorCode errorCode) {
        super(errorCode.getReasonPhrase());
        this.errorCode = errorCode;
    }

    protected GenericException(ErrorCode errorCode, String message) {
        super(buildMessage(message, errorCode));
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    private static String buildMessage(String message, ErrorCode errorCode) {
        return errorCode.getReasonPhrase() + COMMA + SPACE + message;
    }
}
