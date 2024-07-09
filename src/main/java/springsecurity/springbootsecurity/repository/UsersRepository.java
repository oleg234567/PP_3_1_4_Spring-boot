package springsecurity.springbootsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import springsecurity.springbootsecurity.model.User;
import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<User, Long> {
     @Query("Select u from User u left join fetch u.roles where u.email=:email")
     User findByEmail(String email);

     @Query("SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.roles")
     List<User> findAllUniqueUsers();

}