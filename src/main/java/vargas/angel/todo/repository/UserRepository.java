package vargas.angel.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vargas.angel.todo.dto.User;

public interface UserRepository extends JpaRepository<User, Long> {
    //User findByEmailAndActive(String email, boolean isActive);
    User findByEmailAndActiveIsTrue(String email);
}
