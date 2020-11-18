package vargas.angel.todo.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import vargas.angel.todo.dto.User;
import vargas.angel.todo.email.EmailManager;
import vargas.angel.todo.email.EmailTemplate;
import vargas.angel.todo.exceptionhandler.exceptions.InvalidUserException;
import vargas.angel.todo.repository.UserRepository;
import vargas.angel.todo.validators.UserValidator;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

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
        UserValidator userValidator = new UserValidator(user);
        userValidator.validate();
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
        return null;
    }

    public void activateAccount(User user) {
        userRepository.activateUserAccount(user.getId());
    }
}
