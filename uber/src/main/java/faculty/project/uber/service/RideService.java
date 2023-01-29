package faculty.project.uber.service;

import faculty.project.uber.dto.ride.response.RideBasicResponse;
import faculty.project.uber.dto.ride.response.RideDetailedResponse;
import faculty.project.uber.model.others.Ride;

import java.util.List;

public interface RideService {
    List<RideBasicResponse> findByClient(Long clientId);

    List<RideBasicResponse> findByDriver(Long driverId);
    List<RideBasicResponse> findByUsername(String username);
    Ride findById(Long id);
    RideDetailedResponse findDetailedRideById(Long id);
}
