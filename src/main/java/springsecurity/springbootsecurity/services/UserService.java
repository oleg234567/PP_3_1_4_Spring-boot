package springsecurity.springbootsecurity.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import springsecurity.springbootsecurity.model.User;
import java.util.List;

public interface UserService extends UserDetailsService {

    List<User> findAllUsers();

    User findByUsername(String email);

    boolean deleteUser(Long id);

    boolean saveUser(User user);

    void updateUser(User user, Long id);

}