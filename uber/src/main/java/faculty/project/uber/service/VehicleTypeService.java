package faculty.project.uber.service;

import faculty.project.uber.model.others.VehicleType;

import java.util.List;

public interface VehicleTypeService {

    VehicleType findById(Long id);

    List<VehicleType> findAll();
}
