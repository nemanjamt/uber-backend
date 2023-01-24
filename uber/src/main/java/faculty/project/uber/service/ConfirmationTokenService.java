package faculty.project.uber.service;

import faculty.project.uber.model.others.ConfirmationToken;
import faculty.project.uber.model.users.Client;

public interface ConfirmationTokenService {
    ConfirmationToken createToken(Client c);

    ConfirmationToken findByToken(String token);
}
