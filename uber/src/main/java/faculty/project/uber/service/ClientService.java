package faculty.project.uber.service;

import faculty.project.uber.dto.client.request.ClientRegistrationRequest;
import faculty.project.uber.dto.user.response.ReadUserResponse;
import faculty.project.uber.model.users.Client;

import java.util.List;

public interface ClientService {
    List<ReadUserResponse> findAll();
    Client registrateClient(ClientRegistrationRequest request);

    void confirmRegistration(String token);
    Client findById(Long id);

    boolean existsByUsername(String username);

    Client findByUsername(String username);
}
