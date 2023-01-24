package faculty.project.uber.service;

import faculty.project.uber.model.others.ResetPasswordToken;
import faculty.project.uber.model.users.User;

public interface ResetPasswordTokenService {
    public ResetPasswordToken createToken(User u);
    public ResetPasswordToken findByToken(String token);
}
