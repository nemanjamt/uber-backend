package faculty.project.uber.service;

import faculty.project.uber.dto.driver.request.DriverDataChangeRequestDto;
import faculty.project.uber.dto.user.request.ChangeUserDataRequest;

import java.util.List;

public interface DriverDataChangeRequestService {

    void createDriverDataChangeRequest(Long driverId, ChangeUserDataRequest request);
    void changeExistingRequest(Long id, ChangeUserDataRequest req);

    DriverDataChangeRequestDto findByDriver(Long id);

    List<DriverDataChangeRequestDto> findAllRequests();

    void approveDataChangeRequest(Long id);
    void rejectDataChangeRequest(Long id);
}
