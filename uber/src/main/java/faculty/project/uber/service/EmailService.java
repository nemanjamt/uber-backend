package faculty.project.uber.service;

import faculty.project.uber.model.users.Client;
import faculty.project.uber.model.users.User;

public interface EmailService {
    public void sendRegistrationEmail(Client c);

}
