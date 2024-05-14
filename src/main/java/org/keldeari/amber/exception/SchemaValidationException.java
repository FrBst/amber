package org.keldeari.amber.exception;

public class SchemaValidationException extends AmberException {

    public SchemaValidationException(String message) {
        super(message);
    }

    public SchemaValidationException(String message, Throwable reason) {
        super(message, reason);
    }

    public SchemaValidationException(Throwable reason) {
        super(reason);
    }
}
