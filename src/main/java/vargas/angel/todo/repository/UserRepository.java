package vargas.angel.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import vargas.angel.todo.entities.User;

import javax.transaction.Transactional;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmailAndActiveIsTrue(String email);

    @Transactional
    @Modifying
    @Query("update User u set u.active = true where u.id = ?1")
    void activateUserAccount(Long id);
}
