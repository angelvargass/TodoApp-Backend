package vargas.angel.todo.apicontrollers;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vargas.angel.todo.dto.UserDto;
import vargas.angel.todo.entities.User;
import vargas.angel.todo.services.UserService;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/api/v1/users")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostMapping(path = "/login")
    public ResponseEntity<User> login(@RequestBody User user) {
        User dbUser = userService.login(user);
        return new ResponseEntity<>(dbUser, HttpStatus.ACCEPTED);
    }

    @PostMapping()
    public ResponseEntity<User> register(@RequestBody User user) {
        user = userService.register(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PatchMapping(path = "/activate")
    public ResponseEntity<HttpStatus> activateAccount(@RequestBody User user) {
        userService.activateAccount(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<User>> getUsers() {
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    private User convertToEntity(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }

    private UserDto convertToDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }
}
