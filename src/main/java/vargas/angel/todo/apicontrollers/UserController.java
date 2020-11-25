package vargas.angel.todo.apicontrollers;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vargas.angel.todo.dto.UserDto;
import vargas.angel.todo.entities.User;
import vargas.angel.todo.exceptionhandler.exceptions.InvalidUserException;
import vargas.angel.todo.services.UserService;

import javax.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/api/v1/users")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> login(@RequestBody UserDto userDto) {
        User user = convertToEntity(userDto);
        User dbUser = userService.login(user);
        if (dbUser != null) {
            return ResponseEntity.ok(dbUser);
        } else {
            return new ResponseEntity<>(new InvalidUserException("Invalid credentials"),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping()
    public ResponseEntity<?> register(@Valid @RequestBody UserDto userDto) {
        User user = convertToEntity(userDto);
        try {
            user = userService.register(user);
            return ResponseEntity.ok(convertToDto(user));
        }
        catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping(path = "/activate")
    public ResponseEntity<?> activateAccount(@RequestBody UserDto userDto) {
        User user = convertToEntity(userDto);
        try {
            userService.activateAccount(user);
            return ResponseEntity.ok(200);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    private User convertToEntity(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }

    private UserDto convertToDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }
}
