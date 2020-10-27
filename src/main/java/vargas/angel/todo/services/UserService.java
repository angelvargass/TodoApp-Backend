package vargas.angel.todo.services;

import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import vargas.angel.todo.dto.User;
import vargas.angel.todo.email.EmailInformation;
import vargas.angel.todo.email.EmailManager;
import vargas.angel.todo.email.EmailTemplate;
import vargas.angel.todo.repository.UserRepository;

@Service
public class UserService {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserRepository userRepository;
    private EmailManager emailManager;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder,
                       EmailManager emailManager) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.emailManager = emailManager;
    }

    public User register(User user) throws EmailException {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user = userRepository.save(user);
        user.setPassword(null);

        emailManager.sendEmail(new EmailTemplate().newUser(user));
        return user;
    }

    public User login(User user) {
        User dbUser = userRepository.findByEmailAndActiveIsTrue(user.getEmail());
        if(dbUser != null) {
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
