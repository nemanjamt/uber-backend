package faculty.project.uber.service.implementation;

import faculty.project.uber.model.others.Role;
import faculty.project.uber.repository.RoleRepository;
import faculty.project.uber.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public Role findRoleByName(String name) {
        return roleRepository.findByName(name);
    }
}
