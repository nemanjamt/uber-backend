package faculty.project.uber.service.implementation;

import faculty.project.uber.model.others.VehicleType;
import faculty.project.uber.repository.VehicleTypeRepository;
import faculty.project.uber.service.VehicleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class VehicleTypeServiceImpl implements VehicleTypeService {

    @Autowired
    VehicleTypeRepository vehicleTypeRepository;

    @Override
    public VehicleType findById(Long id) {
        if(!vehicleTypeRepository.existsById(id)){
            throw new EntityNotFoundException("Vehicle type with specified id does not exists");
        }
        return vehicleTypeRepository.findById(id).get();
    }

    @Override
    public List<VehicleType> findAll() {
        return vehicleTypeRepository.findAll();
    }
}
