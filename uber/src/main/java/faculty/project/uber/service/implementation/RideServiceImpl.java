package faculty.project.uber.service.implementation;

import faculty.project.uber.dto.ride.response.RideBasicResponse;
import faculty.project.uber.dto.ride.response.RideDetailedResponse;
import faculty.project.uber.model.others.Ride;
import faculty.project.uber.repository.RideRepository;
import faculty.project.uber.service.ClientService;
import faculty.project.uber.service.DriverService;
import faculty.project.uber.service.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class RideServiceImpl implements RideService {
    @Autowired
    RideRepository rideRepository;




    @Override
    public List<RideBasicResponse> findByClient(Long clientId) {
        return rideRepository.findByClientId(clientId).stream().map(r -> new RideBasicResponse(r)).toList();
    }

    @Override
    public List<RideBasicResponse> findByDriver(Long driverId) {
        return rideRepository.findByDriverId(driverId).stream().map(r -> new RideBasicResponse(r)).toList();
    }

    @Override
    public List<RideBasicResponse> findByUsername(String username) {
        return rideRepository.findByUsersUsername(username).stream().map(r -> new RideBasicResponse(r)).toList();
    }

    @Override
    public Ride findById(Long id) {
        if(!rideRepository.existsById(id)){
            throw new EntityNotFoundException("RIDE WITH SPECIFIED ID DOES NOT EXISTS");
        }
        return rideRepository.findById(id).get();
    }
    @Override
    public RideDetailedResponse findDetailedRideById(Long id) {

        Ride r = findById(id);
        RideDetailedResponse detailedRide = new RideDetailedResponse(r);
        return detailedRide;
    }
}
