package faculty.project.uber.service;

import faculty.project.uber.dto.ride.response.RideBasicResponse;

import java.util.List;

public interface RideService {
    List<RideBasicResponse> findByClient(Long clientId);

    List<RideBasicResponse> findByDriver(Long driverId);
    List<RideBasicResponse> findByUsername(String username);
}
