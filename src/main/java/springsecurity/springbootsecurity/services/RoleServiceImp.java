package springsecurity.springbootsecurity.services;

import org.springframework.stereotype.Service;
import springsecurity.springbootsecurity.model.Role;
import springsecurity.springbootsecurity.repository.RolesRepository;
import java.util.List;


@Service
public class RoleServiceImp implements RoleService {
    private final RolesRepository rolesRepository;

    public RoleServiceImp(RolesRepository roleRepository) {
        this.rolesRepository = roleRepository;
    }

    @Override
    public List<Role> findAll() {
        return rolesRepository.findAll();
    }
}
