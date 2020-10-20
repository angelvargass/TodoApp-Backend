package vargas.angel.todo.validators;

import vargas.angel.todo.dto.User;
import vargas.angel.todo.exceptionhandler.exceptions.InvalidUserException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static vargas.angel.todo.validators.ValidationErrorMessages.*;

public class UserValidator {

    private User user;

    public UserValidator(User user) {
        this.user = user;
    }

    public void validate() {
        validateEmail();
        validatePassword();
        validateName();
    }

    private void validateEmail() {
        Pattern pattern = Pattern.compile("^.+@.+\\..+$");
        Matcher matcher = pattern.matcher(user.getEmail());
        if(!matcher.find()) {
            throw new InvalidUserException(USER_EMAIL_INVALID.toString());
        }
    }

    private void validatePassword() {
        String password = user.getPassword();
        if(password == null) {
            throw new InvalidUserException(USER_PASSWORD_EMPTY.toString());
        }
        if(password.isBlank()) {
            throw new InvalidUserException(USER_PASSWORD_EMPTY.toString());
        }
        if(password.length() < 8) {
            throw new InvalidUserException(USER_PASSWORD_LENGTH.toString());
        }
    }

    private void validateName() {
        String name = user.getName();
        String lastName = user.getLastName();
        if(name == null || lastName == null) {
            throw new InvalidUserException(USER_NAME_INVALID.toString());
        }
        if(name.isBlank() || lastName.isBlank()) {
            throw new InvalidUserException(USER_NAME_INVALID.toString());
        }
        if(name.length() < 3 || lastName.length() < 3) {
            throw new InvalidUserException(USER_NAME_LENGTH.toString());
        }
    }
}
