package vargas.angel.todo.services;

import org.springframework.stereotype.Service;
import vargas.angel.todo.entities.Task;
import vargas.angel.todo.entities.User;
import vargas.angel.todo.exceptionhandler.exceptions.InvalidUserException;
import vargas.angel.todo.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final UserService userService;
    private final TaskRepository taskRepository;

    public TaskService(UserService userService, TaskRepository taskRepository) {
        this.userService = userService;
        this.taskRepository = taskRepository;
    }

    public Task create(Long userId, Task task) {
        User user = userService.getById(userId);
        task.setUser(user);
        return taskRepository.save(task);
    }

    public List<Task> getAll() {
        return taskRepository.findAll();
    }


}
