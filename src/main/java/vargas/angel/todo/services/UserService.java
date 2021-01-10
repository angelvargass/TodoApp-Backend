package vargas.angel.todo.services;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import vargas.angel.todo.entities.User;
import vargas.angel.todo.email.EmailManager;
import vargas.angel.todo.exceptionhandler.exceptions.InvalidUserException;
import vargas.angel.todo.repository.UserRepository;

import java.util.List;
import java.util.Optional;

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
        //emailManager.sendEmail(new EmailTemplate().newUser(user));
        return user;
    }


    public User login(User user) {
        Optional<User> dbUser = userRepository.findByEmailAndActiveIsTrue(user.getEmail());

        if (dbUser.isPresent()) {
            if (bCryptPasswordEncoder.matches(user.getPassword(), dbUser.get().getPassword())) {
                return dbUser.get();
            } else {
                throw new InvalidUserException("Invalid credentials", HttpStatus.NOT_ACCEPTABLE);
            }
        }
        throw new InvalidUserException("User not found", HttpStatus.NOT_FOUND);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent()) {
            throw new InvalidUserException("User not found", HttpStatus.NOT_FOUND);
        }
        return user.get();
    }

    public void activateAccount(User user) {
        userRepository.activateUserAccount(user.getId());
    }
}
