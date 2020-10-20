package vargas.angel.todo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import vargas.angel.todo.dto.User;
import vargas.angel.todo.repository.UserRepository;

@Service
public class UserService {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User register(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user = userRepository.save(user);
        user.setPassword(null);
        return user;
    }

    public User login(User user) {
        User dbUser = userRepository.findByEmail(user.getEmail());
        if(bCryptPasswordEncoder.matches(user.getPassword(), dbUser.getPassword())) {
            dbUser.setPassword(null);
            return dbUser;
        }
        return null;
    }
}
