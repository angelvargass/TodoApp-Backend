package vargas.angel.todo.validators;

public enum ValidationErrorMessages {
    USER_PASSWORD_EMPTY("Password is empty"),
    USER_PASSWORD_LENGTH("Password must be 8 characters long"),
    USER_EMAIL_INVALID("Invalid email format"),
    USER_NAME_INVALID("Empty or invalid name format"),
    USER_NAME_LENGTH("Name must be at least 3 characters long");

    private final String error;

    ValidationErrorMessages(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return error;
    }
}
