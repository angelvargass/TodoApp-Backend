package vargas.angel.todo.apicontrollers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vargas.angel.todo.entities.Task;
import vargas.angel.todo.exceptionhandler.exceptions.InvalidUserException;
import vargas.angel.todo.services.TaskService;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/api/v1/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<Task> create(
            @RequestParam(name = "id") long userId,
            @RequestBody Task task) {
        return new ResponseEntity<>(taskService.create(userId, task), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAll() {
        return new ResponseEntity<>(taskService.getAll(), HttpStatus.OK);
    }
}
