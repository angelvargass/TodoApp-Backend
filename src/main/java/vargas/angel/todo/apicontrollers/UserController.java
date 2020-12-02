package vargas.angel.todo.apicontrollers;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vargas.angel.todo.dto.UserDto;
import vargas.angel.todo.entities.User;
import vargas.angel.todo.services.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

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
    public ResponseEntity<UserDto> login(@RequestBody UserDto userDto) {
        User user = convertToEntity(userDto);
        User dbUser = userService.login(user);
        return new ResponseEntity<>(convertToDto(dbUser), HttpStatus.ACCEPTED);
    }

    @PostMapping()
    public ResponseEntity<UserDto> register(@Valid @RequestBody UserDto userDto) {
        User user = convertToEntity(userDto);
        user = userService.register(user);
        return new ResponseEntity<>(convertToDto(user), HttpStatus.CREATED);
    }

    @PatchMapping(path = "/activate")
    public ResponseEntity<HttpStatus> activateAccount(@RequestBody UserDto userDto) {
        User user = convertToEntity(userDto);
        userService.activateAccount(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<UserDto>> getUsers() {
        List<UserDto> users = userService.getUsers().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    private User convertToEntity(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }

    private UserDto convertToDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }
}
