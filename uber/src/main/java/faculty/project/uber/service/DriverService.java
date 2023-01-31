package faculty.project.uber.service;

import faculty.project.uber.dto.driver.request.DriverCreateRequestDto;
import faculty.project.uber.dto.user.response.ReadUserResponse;
import faculty.project.uber.model.users.Driver;

import java.util.List;

public interface DriverService {
    Driver findById(Long id);
    boolean existsById(Long id);

    void saveDriver(Driver driver);
    List<ReadUserResponse> findAll();

    boolean existsByUsername(String username);

    Driver findByUsername(String username);

    Driver createDriver(DriverCreateRequestDto driverCreateRequestDto);

    void activateDriver(Long id);

    void deactivateDriver(Long id);

    boolean existsByEmail(String email);
}
