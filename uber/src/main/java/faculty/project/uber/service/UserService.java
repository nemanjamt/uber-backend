package faculty.project.uber.service;

import faculty.project.uber.model.users.User;

public interface UserService {
    User findOne(Long id);
}
