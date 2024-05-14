package org.keldeari.amber.exception;

public class DatapointValidationException extends AmberException {

    public DatapointValidationException(String message) {
        super(message);
    }

    public DatapointValidationException(String message, Throwable reason) {
        super(message, reason);
    }

    public DatapointValidationException(Throwable reason) {
        super(reason);
    }
}
