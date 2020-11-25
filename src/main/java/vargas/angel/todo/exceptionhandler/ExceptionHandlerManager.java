package vargas.angel.todo.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import vargas.angel.todo.exceptionhandler.exceptions.InvalidUserException;

@ControllerAdvice
public class ExceptionHandlerManager {

    @ExceptionHandler(value = InvalidUserException.class)
    public ResponseEntity<?> invalidUserResponse(InvalidUserException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<?> invalidArgumentResponse(MethodArgumentNotValidException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
