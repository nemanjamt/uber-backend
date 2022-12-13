package faculty.project.uber.service;

import faculty.project.uber.dto.user.request.ChangePasswordRequest;
import faculty.project.uber.dto.user.request.ChangeUserDataRequest;
import faculty.project.uber.dto.user.response.ReadUserResponse;
import faculty.project.uber.model.users.User;

public interface UserService {
    ReadUserResponse findOne(Long id);
    ReadUserResponse changeUserData(Long id, ChangeUserDataRequest req) ;

    void changePassword(Long id, ChangePasswordRequest req);
}
