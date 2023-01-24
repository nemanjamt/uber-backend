package faculty.project.uber.service.implementation;

import faculty.project.uber.model.others.ResetPasswordToken;
import faculty.project.uber.model.users.User;
import faculty.project.uber.repository.ResetPasswordTokenRepository;
import faculty.project.uber.service.ResetPasswordTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

@Service
public class ResetPasswordTokenServiceImpl implements ResetPasswordTokenService {

    @Autowired
    ResetPasswordTokenRepository resetPasswordTokenRepository;

    @Override
    public ResetPasswordToken createToken(User u){
        ResetPasswordToken token = new ResetPasswordToken();
        token.setUser(u);
        String t = UUID.randomUUID().toString();
        token.setToken(t);
        resetPasswordTokenRepository.save(token);
        return token;
    }

    @Override
    public ResetPasswordToken findByToken(String token) {
        if(!resetPasswordTokenRepository.existsByToken(token)){
            throw new EntityNotFoundException("Token does not exists");
        }
        return resetPasswordTokenRepository.findByToken(token);
    }
}
