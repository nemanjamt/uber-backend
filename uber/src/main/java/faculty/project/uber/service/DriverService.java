package faculty.project.uber.service;

import faculty.project.uber.dto.user.response.ReadUserResponse;
import faculty.project.uber.model.users.Driver;

import java.util.List;

public interface DriverService {
    Driver findById(Long id);
    boolean existsById(Long id);

    void saveDriver(Driver driver);
    List<ReadUserResponse> findAll();

    void activateDriver(Long id);

    void deactivateDriver(Long id);
}
