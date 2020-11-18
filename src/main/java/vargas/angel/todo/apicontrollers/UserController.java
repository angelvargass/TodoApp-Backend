package vargas.angel.todo.apicontrollers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import vargas.angel.todo.dto.User;
import vargas.angel.todo.exceptionhandler.exceptions.InvalidUserException;
import vargas.angel.todo.services.UserService;
import vargas.angel.todo.validators.UserValidator;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/api/v1/users")
public class UserController {

    private final UserService userService;

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

    @PostMapping()
    public ResponseEntity<?> register(@NonNull @RequestBody User user) {
        try {
            user = userService.register(user);
            return ResponseEntity.ok(user);
        } catch (InvalidUserException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping(path = "/activate")
    public ResponseEntity<?> activateAccount(@NonNull @RequestBody User user) {
        UserValidator userValidator = new UserValidator(user);

        try {
            userValidator.validateId();
            userService.activateAccount(user);
            return ResponseEntity.ok(200);
        } catch (InvalidUserException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }
    }
}
