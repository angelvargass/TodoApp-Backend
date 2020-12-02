package vargas.angel.todo.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import vargas.angel.todo.entities.User;
import vargas.angel.todo.email.EmailManager;
import vargas.angel.todo.email.EmailTemplate;
import vargas.angel.todo.exceptionhandler.exceptions.InvalidUserException;
import vargas.angel.todo.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;
    private final EmailManager emailManager;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder,
                       EmailManager emailManager) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.emailManager = emailManager;
    }

    public User register(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user = userRepository.save(user);
        user.setPassword(null);
        emailManager.sendEmail(new EmailTemplate().newUser(user));
        return user;
    }

    public User login(User user) {
        User dbUser = userRepository.findByEmailAndActiveIsTrue(user.getEmail());
        if (dbUser != null) {
            if (bCryptPasswordEncoder.matches(user.getPassword(), dbUser.getPassword())) {
                dbUser.setPassword(null);
                return dbUser;
            }
        }
        throw new InvalidUserException("Invalid credentials");
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public void activateAccount(User user) {
        userRepository.activateUserAccount(user.getId());
    }
}
