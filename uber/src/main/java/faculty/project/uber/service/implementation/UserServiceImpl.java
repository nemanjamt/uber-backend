package faculty.project.uber.service.implementation;

import faculty.project.uber.dto.user.request.ChangePasswordRequest;
import faculty.project.uber.dto.user.request.ChangeUserDataRequest;
import faculty.project.uber.dto.user.response.ReadUserResponse;
import faculty.project.uber.model.users.User;
import faculty.project.uber.repository.UserRepository;
import faculty.project.uber.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
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
        String newPassword = req.getNewPassword();
        u.setPassword(newPassword);

        userRepository.save(u);
    }
}
