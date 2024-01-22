package org.keldeari.amber.exception;

public class AmberException extends RuntimeException {

    public AmberException() {}
    
    public AmberException(String message) {
        super(message);
    }

    public AmberException(String message, Throwable reason) {
        super(message, reason);
    }

    public AmberException(Throwable reason) {
        super(reason);
    }
}
