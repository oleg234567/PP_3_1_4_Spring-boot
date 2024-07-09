package springsecurity.springbootsecurity.services;


import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import springsecurity.springbootsecurity.model.User;
import springsecurity.springbootsecurity.repository.UsersRepository;
import javax.transaction.Transactional;
import java.util.List;


@Service
public class UserServiceImp implements UserService {

    private final UsersRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImp(UsersRepository userRepository, @Lazy PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> findAll() { return userRepository.findAllUniqueUsers(); }

    @Override
    @Transactional
    public boolean deleteUser(Long id) {
        if(userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public boolean saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    @Override
    public User findByUsername(String email) {
        return userRepository.findByEmail(email);
    }

    @Transactional
    @Override
    public void updateUser(User updateUser, Long id) {
        updateUser.setId(id);
        if (!updateUser.getPassword().equals(userRepository.findById(id).get().getPassword())) {
            updateUser.setPassword(passwordEncoder.encode(updateUser.getPassword()));
        }
        userRepository.save(updateUser);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername (String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User '%s' не найден", email));
        }
        return user;
    }
}
