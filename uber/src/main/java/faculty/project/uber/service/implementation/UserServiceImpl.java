package faculty.project.uber.service.implementation;

import faculty.project.uber.dto.authentication.request.SignUpRequest;
import faculty.project.uber.dto.authentication.response.LocalUser;
import faculty.project.uber.dto.user.request.ChangePasswordRequest;
import faculty.project.uber.dto.user.request.ChangeUserDataRequest;
import faculty.project.uber.dto.user.response.ReadUserResponse;
import faculty.project.uber.exceptions.OAuth2AuthenticationProcessingException;
import faculty.project.uber.exceptions.UserAlreadyExistsAuthenticationException;
import faculty.project.uber.model.others.Role;
import faculty.project.uber.model.users.Client;
import faculty.project.uber.model.users.User;
import faculty.project.uber.repository.RoleRepository;
import faculty.project.uber.repository.UserRepository;
import faculty.project.uber.security.oauth2.user.OAuth2UserInfo;
import faculty.project.uber.security.oauth2.user.OAuth2UserInfoFactory;
import faculty.project.uber.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    private RoleRepository roleRepository;
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public ReadUserResponse findOne(Long id) {
        return  new ReadUserResponse(userRepository.findById(id).get());
    }

    @Override
    public ReadUserResponse changeUserData(Long id, ChangeUserDataRequest req) {
        User u = userRepository.findById(id).get();
        u.setName(req.getName());
        u.setLastName(req.getLastName());
        u.setPhone(req.getPhone());
        u.setAddress(req.getAddress());
        User savedUser = userRepository.save(u);
        return new ReadUserResponse(savedUser);
    }

    @Override
    public void changePassword(Long id, ChangePasswordRequest req) {
        User u = userRepository.findById(id).get();
//        if(!passwordEncoder.matches(req.getCurrentPassword(), u.getPassword())){
//            throw new BadPassword("bad password");
//        }
//        String newPassword = passwordEncoder.encode(req.getNewPassword());
        String newPassword = passwordEncoder.encode(req.getNewPassword());
        u.setPassword(newPassword);

        userRepository.save(u);
    }

    private User updateExistingUser(User existingUser, OAuth2UserInfo oAuth2UserInfo) {
        String[] fullName = oAuth2UserInfo.getName().split(" ");
        existingUser.setName(fullName[0]);
        existingUser.setLastName(fullName[1]);
        return userRepository.save(existingUser);
    }

    @Override
    @Transactional(value = "transactionManager")
    public User registerNewUser(final SignUpRequest signUpRequest) throws UserAlreadyExistsAuthenticationException {
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new UserAlreadyExistsAuthenticationException("User with email id " + signUpRequest.getEmail() + " already exist");
        }
        User user = buildUser(signUpRequest);
        user = userRepository.save(user);
        userRepository.flush();
        return user;
    }

    private User buildUser(final SignUpRequest formDTO) {
        User user = new Client();
        String[] fullName = formDTO.getName().split(" ");
        try{
            user.setName(fullName[0]);
            user.setLastName(fullName[1]);
        }catch(IndexOutOfBoundsException e){
            user.setName("");
            user.setLastName("");
        }

        user.setEmail(formDTO.getEmail());
        List<Role> roles = new ArrayList<>();
        Role r = roleRepository.findById(1L).get();
        roles.add(r);
        user.setRoles(roles);
        user.setPhone("");
        user.setAddress("");
        user.setPassword("");
        user.setUsername(formDTO.getEmail());
        return user;
    }
    @Override
    public LocalUser processUserRegistration(String registrationId, Map<String, Object> attributes, OidcIdToken idToken, OidcUserInfo userInfo) throws UserAlreadyExistsAuthenticationException {
        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(registrationId, attributes);
        if (StringUtils.isEmpty(oAuth2UserInfo.getName())) {
            throw new OAuth2AuthenticationProcessingException("Name not found from OAuth2 provider");
        } else if (StringUtils.isEmpty(oAuth2UserInfo.getEmail())) {
            throw new OAuth2AuthenticationProcessingException("Email not found from OAuth2 provider");
        }
        SignUpRequest userDetails = toUserRegistrationObject(registrationId, oAuth2UserInfo);
        User user = findUserByEmail(oAuth2UserInfo.getEmail());
        if (user != null) {
            user = updateExistingUser(user, oAuth2UserInfo);
        } else {
            user = registerNewUser(userDetails);
        }

        return LocalUser.create(user, attributes, idToken, userInfo);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    private SignUpRequest toUserRegistrationObject(String registrationId, OAuth2UserInfo oAuth2UserInfo) {
        SignUpRequest s = new SignUpRequest();
        System.out.println("EVO 2");
        System.out.println(oAuth2UserInfo.getAttributes());
        System.out.println(oAuth2UserInfo.getAttributes().get("family_name"));
        System.out.println(oAuth2UserInfo.getAttributes().get("given_name"));
        s.setEmail(oAuth2UserInfo.getEmail());
        s.setName(oAuth2UserInfo.getName());
//        return SignUpRequest.getBuilder().addProviderUserID(oAuth2UserInfo.getId()).addDisplayName(oAuth2UserInfo.getName()).addEmail(oAuth2UserInfo.getEmail())
//                .addSocialProvider(GeneralUtils.toSocialProvider(registrationId)).addPassword("changeit").build();
        return s;
    }
}
