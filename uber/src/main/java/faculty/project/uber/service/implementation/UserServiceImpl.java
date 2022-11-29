package faculty.project.uber.service.implementation;

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
    public User findOne(Long id) {
        return userRepository.findById(id).get();
    }
}
