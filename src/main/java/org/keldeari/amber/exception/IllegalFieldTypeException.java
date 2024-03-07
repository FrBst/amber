package org.keldeari.amber.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Illegal field type")
public class IllegalFieldTypeException extends AmberException {
    public IllegalFieldTypeException(String message) {
        super(message);
    }
}
