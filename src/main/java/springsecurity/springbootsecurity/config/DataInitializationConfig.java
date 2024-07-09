package springsecurity.springbootsecurity.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import springsecurity.springbootsecurity.model.Role;
import springsecurity.springbootsecurity.model.User;
import springsecurity.springbootsecurity.repository.RolesRepository;
import springsecurity.springbootsecurity.repository.UsersRepository;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class DataInitializationConfig {

    private final RolesRepository roleRepository;
    private final UsersRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializationConfig(RolesRepository roleRepository, UsersRepository userRepository, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public void initializeData() {
        Role role1 = new Role();
        role1.setRole("ROLE_USER");
        Role role2 = new Role();
        role2.setRole("ROLE_ADMIN");

        roleRepository.save(role1);
        roleRepository.save(role2);

        List<Role> adminRoles = new ArrayList<>();
        adminRoles.add(role1);
        adminRoles.add(role2);
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(role1);

        User admin = new User();
        admin.setPassword(passwordEncoder.encode("12345"));
        admin.setRoles(adminRoles);
        admin.setFirstName("admin");
        admin.setEmail("admin@mail.ru");
        admin.setAge((byte) 0);
        userRepository.save(admin);

        User user = new User();
        user.setPassword(passwordEncoder.encode("54321"));
        user.setRoles(userRoles);
        user.setFirstName("oleg");
        user.setEmail("oleg@mail.ru");
        user.setAge((byte) 26);
        userRepository.save(user);
    }
}

