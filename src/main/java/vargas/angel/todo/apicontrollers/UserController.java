package vargas.angel.todo.apicontrollers;

import org.apache.commons.mail.EmailException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import vargas.angel.todo.dto.User;
import vargas.angel.todo.exceptionhandler.exceptions.InvalidUserException;
import vargas.angel.todo.services.UserService;
import vargas.angel.todo.validators.UserValidator;

@RestController
@RequestMapping(path = "/api/v1/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/login")
    public ResponseEntity<User> login(@NonNull @RequestBody User user) {
        User dbUser = userService.login(user);
        if (dbUser != null) {
            return ResponseEntity.ok(dbUser);
        } else {
            throw new InvalidUserException("Invalid credentials");
        }
    }

    @PostMapping(path = "/sign-up")
    public ResponseEntity<User> register(@NonNull @RequestBody User user) throws EmailException {
        UserValidator userValidator = new UserValidator(user);

        try {
            userValidator.validate();
            user = userService.register(user);
            return ResponseEntity.ok(user);
        } catch (InvalidUserException e) {
            throw e;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }
}
