package de.msg.webapp.controllers.errorhandling;

import de.msg.webapp.services.PersonenServiceException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        final Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        final List<String> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(x -> x.getField() + ": " + x.getDefaultMessage()).collect(Collectors.toList());
        body.put("errors", errors);
        logger.warn("Übel, übel", ex);  // wichtig
        return ResponseEntity.badRequest().body(body);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGenericException(final Exception ex, final WebRequest request) {
        final Map<String, Object> body = new HashMap<>();

        body.put("timestamp", LocalDateTime.now());
        body.put("message", ex.getMessage());
        body.put("ErrorType", ex.getClass().getSimpleName());

        logger.error("Upps", ex);// Wichtig

        return ResponseEntity.badRequest().body(body);
    }
    @ExceptionHandler(PersonenServiceException.class)
    public ResponseEntity<Object> handlePersonenServiceException(final PersonenServiceException ex, final WebRequest request) {
        final Map<String, Object> body = new HashMap<>();

        body.put("timestamp", LocalDateTime.now());
        body.put("message", ex.getMessage());
        body.put("ErrorType", ex.getClass().getSimpleName());

        logger.error("Upps", ex);// Wichtig

        if(ex.getMessage().equals("Antipath"))
            return ResponseEntity.badRequest().body(body);
        return ResponseEntity.internalServerError().body(body);
    }
}
