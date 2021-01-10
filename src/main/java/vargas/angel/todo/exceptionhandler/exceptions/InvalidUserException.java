package vargas.angel.todo.exceptionhandler.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidUserException extends RuntimeException {
    private HttpStatus httpStatus;
    public InvalidUserException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
