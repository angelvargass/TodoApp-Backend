package vargas.angel.todo.exceptionhandler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import vargas.angel.todo.exceptionhandler.exceptions.InvalidUserException;

import javax.persistence.EntityNotFoundException;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidUserException.class)
    public ResponseEntity<?> handleInvalidUserException(InvalidUserException ex) {
        ApiError apiError = new ApiError(ex.getHttpStatus(), ex.getMessage());
        return new ResponseEntity<>(apiError, ex.getHttpStatus());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleEntityNotFoundException(EntityNotFoundException ex) {
        ApiError apiError = new ApiError(NOT_FOUND, ex.getMessage());
        return new ResponseEntity<>(apiError, NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception ex) {
        ApiError apiError = new ApiError(BAD_REQUEST, ex.getMessage());
        return new ResponseEntity<>(apiError, BAD_REQUEST);
    }
}
