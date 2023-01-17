package faculty.project.uber.service.implementation;

import faculty.project.uber.constants.ErrorConstants;
import faculty.project.uber.dto.driver.request.DriverDataChangeRequestDto;
import faculty.project.uber.dto.user.request.ChangeUserDataRequest;
import faculty.project.uber.model.others.DriverDataChangeRequest;
import faculty.project.uber.model.users.Driver;
import faculty.project.uber.repository.DriverDataChangeRequestRepository;
import faculty.project.uber.service.DriverDataChangeRequestService;
import faculty.project.uber.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class DriverDataChangeRequestServiceImpl implements DriverDataChangeRequestService {
    DriverDataChangeRequestRepository driverDataChangeRequestRepository;
    DriverService driverService;
    @Autowired
    public DriverDataChangeRequestServiceImpl(DriverDataChangeRequestRepository repository, DriverService driverService){
        this.driverDataChangeRequestRepository = repository;
        this.driverService = driverService;
    }

    @Override
    public void createDriverDataChangeRequest(Long driverId, ChangeUserDataRequest request) {
        if(existsByDriver(driverId)){
            DriverDataChangeRequest r = driverDataChangeRequestRepository.findByDriverId(driverId);
            changeExistingRequest(r.getId(), request);
            return;
        }
        Driver driver = driverService.findById(driverId);
        DriverDataChangeRequest dataChangeRequest = DriverDataChangeRequestBuilder(request, driver);
        driverDataChangeRequestRepository.save(dataChangeRequest);
        driver.setDriverDataChangeRequest(dataChangeRequest);
        driverService.saveDriver(driver);
    }

    public boolean existsByDriver(Long driverId){
        return driverDataChangeRequestRepository.existsByDriverId(driverId);
    }

    public boolean existsById(Long id){
        return driverDataChangeRequestRepository.existsById(id);
    }

    public DriverDataChangeRequest findById(Long id){
        if(!existsById(id)){
            throw new EntityNotFoundException(String.format(ErrorConstants.ENTITY_NOT_FOUND,"driver data change request", id));
        }
        return driverDataChangeRequestRepository.findById(id).get();
    }

    @Override
    public void changeExistingRequest(Long id, ChangeUserDataRequest req){
        DriverDataChangeRequest request = findById(id);
        request.setName(req.getName());
        request.setLastName(req.getLastName());
        System.out.println("IZMIJENJENO?");
        request.setAddress(req.getAddress());
        request.setPhone(req.getPhone());
        System.out.println(request.getLastName());
        driverDataChangeRequestRepository.save(request);
    }

    @Override
    public DriverDataChangeRequestDto findByDriver(Long id) {
        if(!existsByDriver(id)){
            throw new EntityNotFoundException(String.format(ErrorConstants.ENTITY_NOT_FOUND,"driver data change request by driver",id));
        }
        Driver d = driverService.findById(id);
        DriverDataChangeRequestDto dto = new DriverDataChangeRequestDto(driverDataChangeRequestRepository.findByDriverId(id), d);
        return dto;
    }

    @Transactional
    @Override
    public List<DriverDataChangeRequestDto> findAllRequests() {
        return driverDataChangeRequestRepository.findAll().stream().map(d -> new DriverDataChangeRequestDto(d,d.getDriver())).toList();
    }

    @Override
    public void approveDataChangeRequest(Long id) {
        DriverDataChangeRequest driverDataChangeRequest = findById(id);
        Driver driver = driverDataChangeRequest.getDriver();
        driver.setAddress(driverDataChangeRequest.getAddress());
        driver.setName(driverDataChangeRequest.getName());
        driver.setPhone(driverDataChangeRequest.getPhone());
        driver.setLastName(driverDataChangeRequest.getLastName());
        driver.setDriverDataChangeRequest(null);
        driverService.saveDriver(driver);
        driverDataChangeRequestRepository.delete(driverDataChangeRequest);
    }

    @Override
    public void rejectDataChangeRequest(Long id) {
        DriverDataChangeRequest d = findById(id);
        Driver driver = d.getDriver();
        driver.setDriverDataChangeRequest(null);
        driverService.saveDriver(driver);
        driverDataChangeRequestRepository.delete(d);
    }


    private DriverDataChangeRequest DriverDataChangeRequestBuilder(ChangeUserDataRequest request, Driver driver){
        DriverDataChangeRequest d = new DriverDataChangeRequest();
        d.setAddress(request.getAddress());
        d.setName(request.getName());
        d.setPhone(request.getPhone());
        d.setLastName(request.getLastName());
        d.setDriver(driver);
        return d;
    }
}
