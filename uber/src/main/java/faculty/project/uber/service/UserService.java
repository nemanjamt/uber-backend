package faculty.project.uber.service;

import faculty.project.uber.dto.authentication.request.SignUpRequest;
import faculty.project.uber.dto.authentication.response.LocalUser;
import faculty.project.uber.dto.user.request.ChangePasswordRequest;
import faculty.project.uber.dto.user.request.ChangeUserDataRequest;
import faculty.project.uber.dto.user.response.ReadUserResponse;
import faculty.project.uber.exceptions.UserAlreadyExistsAuthenticationException;
import faculty.project.uber.model.users.User;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;

import java.util.Map;

public interface UserService {
    ReadUserResponse findOne(Long id);
    ReadUserResponse changeUserData(Long id, ChangeUserDataRequest req) ;

    void changePassword(Long id, ChangePasswordRequest req);
    LocalUser processUserRegistration(String registrationId, Map<String, Object> attributes, OidcIdToken idToken, OidcUserInfo userInfo) throws UserAlreadyExistsAuthenticationException;

    public User registerNewUser(final SignUpRequest signUpRequest) throws UserAlreadyExistsAuthenticationException;

    public User findUserByEmail(String email);

}
