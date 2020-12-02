package vargas.angel.todo.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import vargas.angel.todo.exceptionhandler.exceptions.InvalidUserException;

import java.util.List;

@ControllerAdvice
public class ExceptionHandlerManager {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorResponse> exceptionResponse(Exception e) {
        List<String> details = List.of(e.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse(e.getMessage(), details);
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = InvalidUserException.class)
    public ResponseEntity<ErrorResponse> invalidUserResponse(InvalidUserException e) {
        List<String> details = List.of(e.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse(e.getMessage(), details);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> invalidArgumentResponse(MethodArgumentNotValidException e) {
        List<String> details = List.of(e.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse(e.getMessage(), details);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
