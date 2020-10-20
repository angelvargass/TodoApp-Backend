package vargas.angel.todo.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import vargas.angel.todo.exceptionhandler.exceptions.InvalidUserException;

import java.time.ZonedDateTime;

@ControllerAdvice
public class ExceptionHandlerManager {
    private static final HttpStatus badRequest = HttpStatus.BAD_REQUEST;

    @ExceptionHandler(value = {InvalidUserException.class})
    public ResponseEntity<Object> handleInvalidUserException(InvalidUserException e) {
        ExceptionObject exceptionObject = new ExceptionObject(
                e.getMessage(),
                badRequest,
                ZonedDateTime.now()
        );
        return new ResponseEntity<>(exceptionObject, badRequest);
    }
}
