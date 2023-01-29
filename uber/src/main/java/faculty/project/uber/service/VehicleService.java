package faculty.project.uber.service;

import faculty.project.uber.dto.vehicle.response.VehicleAllAvailableDTO;
import faculty.project.uber.model.others.Vehicle;

import java.util.List;

public interface VehicleService {
    List<VehicleAllAvailableDTO> findAllAvailableCars();

    Vehicle createVehicle(Vehicle v);
}
