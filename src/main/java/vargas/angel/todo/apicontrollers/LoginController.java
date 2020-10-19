package vargas.angel.todo.apicontrollers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/login")
public class LoginController {

    @GetMapping
    public ResponseEntity<?> login() {
        return ResponseEntity.ok().build();
    }
}
