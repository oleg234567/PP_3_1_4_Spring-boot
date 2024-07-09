package springsecurity.springbootsecurity.services;

import springsecurity.springbootsecurity.model.Role;
import java.util.List;


public interface RoleService {
    List <Role> findAll();
}
