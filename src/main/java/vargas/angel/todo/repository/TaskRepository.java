package vargas.angel.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vargas.angel.todo.entities.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
