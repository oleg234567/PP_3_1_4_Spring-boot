package springsecurity.springbootsecurity.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springsecurity.springbootsecurity.model.Role;


@Repository
public interface RolesRepository extends JpaRepository <Role, Long> {
}