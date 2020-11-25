package vargas.angel.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import vargas.angel.todo.entities.User;

import javax.transaction.Transactional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmailAndActiveIsTrue(String email);

    @Transactional
    @Modifying
    @Query("update TBL_USER u set u.active = true where u.id = ?1")
    void activateUserAccount(long id);
}
