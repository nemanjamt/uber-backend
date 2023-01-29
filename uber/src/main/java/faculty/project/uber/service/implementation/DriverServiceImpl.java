package faculty.project.uber.service.implementation;

import faculty.project.uber.constants.ErrorConstants;
import faculty.project.uber.dto.user.response.ReadUserResponse;
import faculty.project.uber.model.users.Driver;
import faculty.project.uber.repository.DriverRepository;
import faculty.project.uber.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class DriverServiceImpl implements DriverService {

    DriverRepository driverRepository;

    @Autowired
    public DriverServiceImpl(DriverRepository driverRepository){
        this.driverRepository = driverRepository;
    }

    @Override
    public Driver findById(Long id) {
        if(!existsById(id)){
            throw new EntityNotFoundException(String.format(ErrorConstants.ENTITY_NOT_FOUND,"Driver",id));
        }

        return driverRepository.findById(id).get();
    }


    @Override
    public boolean existsById(Long id) {

        return driverRepository.existsById(id);
    }

    @Override
    public void saveDriver(Driver driver) {
        driverRepository.save(driver);
    }

    @Override
    public List<ReadUserResponse> findAll() {
        return driverRepository.findAll().stream().map(d -> new ReadUserResponse(d)).toList();
    }

    @Override
    public void activateDriver(Long id) {
        Driver d = findById(id);
        d.setActive(true);
        d.setAvailable(true);
        driverRepository.save(d);
    }

    @Override
    public void deactivateDriver(Long id) {
        Driver d = findById(id);
        d.setActive(false);
        d.setAvailable(false);
        driverRepository.save(d);
    }
}
