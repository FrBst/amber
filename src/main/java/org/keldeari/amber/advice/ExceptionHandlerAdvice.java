package org.keldeari.amber.advice;

import jakarta.servlet.http.HttpServletResponse;
import org.keldeari.amber.exception.DatapointValidationException;
import org.keldeari.amber.exception.SchemaValidationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(DatapointValidationException.class)
    public void handleException(DatapointValidationException exception, HttpServletResponse response) throws IOException {
        String msg = exception.getMessage();
        response.sendError(400, msg);
    }

    @ExceptionHandler(SchemaValidationException.class)
    public void handleException(SchemaValidationException exception, HttpServletResponse response) throws IOException {
        String msg = exception.getMessage();
        response.sendError(400, msg);
    }
}