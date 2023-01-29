package faculty.project.uber.service.implementation;

import faculty.project.uber.dto.vehicle.response.VehicleAllAvailableDTO;
import faculty.project.uber.model.enums.RideStatus;
import faculty.project.uber.model.others.Route;
import faculty.project.uber.model.others.Vehicle;
import faculty.project.uber.model.users.Driver;
import faculty.project.uber.repository.DriverRepository;
import faculty.project.uber.repository.VehicleRepository;
import faculty.project.uber.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private DriverRepository dr;

    @Override
    public List<VehicleAllAvailableDTO> findAllAvailableCars() {
        //GET ALL AVAILABLE DRIVERS
        List<Driver> availableDrivers = dr.findAll().stream().filter(d-> d.getAvailable().equals(true)).collect(Collectors.toList());

        List<Route> routes = availableDrivers.stream()
                .map(d->
                {
                    if(d.getRides().size() ==0){
                        return null;
                    }
                    if(!d.getRides().get(d.getRides().size()-1).getStatus().equals(RideStatus.ACTIVE)){
                        return null;
                    }
                    return d.getRides().get(d.getRides().size()-1).getRoute();

                })
                .collect(Collectors.toList());

        //GET ALL VIECHLES FROM AVAILABLE DRIVERS
        List<Vehicle> vehicles = availableDrivers.stream().map(d -> d.getVehicle()).collect(Collectors.toList());

        AtomicInteger routes_counter = new AtomicInteger();
        //MAKE RESPONSE DTO
        List<VehicleAllAvailableDTO> vaadto = vehicles.stream()
                .map(
                        v -> {
                            Route route = routes.get(routes_counter.get());
                            routes_counter.incrementAndGet();
                            return new VehicleAllAvailableDTO(v.getId(), v.getLicencePlate(), v.getVehicleType().getKind().name(), v.getOccupied(), v.getLocation(), route);
                        }
                )
                .collect(Collectors.toList());

        return vaadto;
    }

    @Override
    public Vehicle createVehicle(Vehicle v) {
        return vehicleRepository.save(v);
    }
}
