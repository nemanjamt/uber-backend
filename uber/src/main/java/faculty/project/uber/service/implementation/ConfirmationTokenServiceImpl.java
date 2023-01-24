package faculty.project.uber.service.implementation;

import faculty.project.uber.model.others.ConfirmationToken;
import faculty.project.uber.model.users.Client;
import faculty.project.uber.repository.ConfirmationTokenRepository;
import faculty.project.uber.service.ConfirmationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class ConfirmationTokenServiceImpl implements ConfirmationTokenService {
    @Autowired
    ConfirmationTokenRepository confirmationTokenRepository;
    @Override
    public ConfirmationToken createToken(Client c) {
        ConfirmationToken token = new ConfirmationToken(c);
        confirmationTokenRepository.save(token);
        return token;
    }

    @Override
    public ConfirmationToken findByToken(String token) {
        if(!confirmationTokenRepository.existsByConfirmationToken(token)){
            throw new EntityNotFoundException("ENTITY WITH SPECIFIED TOKEN DOES NOT EXIST");
        }
        ConfirmationToken c = confirmationTokenRepository.findByConfirmationToken(token);
        return c;
    }
}
