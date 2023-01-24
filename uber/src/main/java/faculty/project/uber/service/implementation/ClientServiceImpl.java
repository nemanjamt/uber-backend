package faculty.project.uber.service.implementation;

import faculty.project.uber.dto.client.request.ClientRegistrationRequest;
import faculty.project.uber.dto.user.response.ReadUserResponse;
import faculty.project.uber.exceptions.EmailAlreadyExistsException;
import faculty.project.uber.exceptions.UsernameAlreadyExists;
import faculty.project.uber.model.others.ConfirmationToken;
import faculty.project.uber.model.others.Role;
import faculty.project.uber.model.users.Client;
import faculty.project.uber.repository.ClientRepository;
import faculty.project.uber.service.ClientService;
import faculty.project.uber.service.ConfirmationTokenService;
import faculty.project.uber.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    ConfirmationTokenService confirmationTokenService;

    @Autowired
    RoleService roleService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    ClientRepository clientRepository;
    @Override
    public List<ReadUserResponse> findAll() {
        return clientRepository.findAll().stream().map(c -> new ReadUserResponse(c)).toList();
    }

    @Override
    public Client registrateClient(ClientRegistrationRequest request) {
        if(clientRepository.existsByEmail(request.getEmail())){
            throw new EmailAlreadyExistsException("Specified email already exists");
        }
        if(clientRepository.existsByUsername(request.getUsername())){
            throw new UsernameAlreadyExists("Client with specified username already exists");
        }
        Client c = new Client();
        c.setEmail(request.getEmail());
        c.setLastName(request.getLastName());
        c.setAddress(request.getAddress());
        c.setActivated(false);
        c.setBlocked(false);
        c.setName(request.getName());
        c.setPhone(request.getPhone());
        c.setPassword(passwordEncoder.encode(request.getPassword()));
        c.setUsername(request.getUsername());
        List<Role> roles = new ArrayList<>();
        roles.add(roleService.findRoleByName("ROLE_CLIENT"));
        c.setRoles(roles);
        clientRepository.save(c);
        return c;
    }

    @Override
    public void confirmRegistration(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService.findByToken(token);
        Client c = confirmationToken.getUserEntity();
        c.setActivated(true);
        clientRepository.save(c);
    }

    @Override
    public Client findById(Long id) {
        if(!clientRepository.existsById(id)){
            throw new EntityNotFoundException("CLIENT WITH SPECIFIED ID DOES NOT EXISTS");
        }
        return clientRepository.findById(id).get();
    }

    @Override
    public boolean existsByUsername(String username) {
        return clientRepository.existsByUsername(username);
    }

    @Override
    public Client findByUsername(String username) {
        if(!existsByUsername(username)){
            throw new EntityNotFoundException("CLIENT WITH SPECIFIED USERNAME DOES NOT EXISTS");
        }
        return clientRepository.findByUsername(username);
    }
}
