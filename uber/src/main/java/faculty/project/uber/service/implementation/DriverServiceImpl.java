package faculty.project.uber.service.implementation;

import faculty.project.uber.constants.ErrorConstants;
import faculty.project.uber.dto.driver.request.DriverCreateRequestDto;
import faculty.project.uber.dto.user.response.ReadUserResponse;
import faculty.project.uber.exceptions.EmailAlreadyExistsException;
import faculty.project.uber.exceptions.UsernameAlreadyExistsException;
import faculty.project.uber.model.others.Role;
import faculty.project.uber.model.others.Vehicle;
import faculty.project.uber.model.others.VehicleType;
import faculty.project.uber.model.users.Driver;
import faculty.project.uber.repository.DriverRepository;
import faculty.project.uber.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DriverServiceImpl implements DriverService {

    DriverRepository driverRepository;

    VehicleService vehicleService;
    VehicleTypeService vehicleTypeService;
    UserService userService;

    PasswordEncoder passwordEncoder;

    RoleService roleService;
    @Autowired
    public DriverServiceImpl(RoleService roleService, PasswordEncoder passwordEncoder, DriverRepository driverRepository, VehicleService vehicleService, VehicleTypeService vehicleTypeService, UserService userService){
        this.driverRepository = driverRepository;
        this.vehicleService = vehicleService;
        this.vehicleTypeService = vehicleTypeService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
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
    public boolean existsByUsername(String username) {
        return driverRepository.existsByUsername(username);
    }

    @Override
    public Driver findByUsername(String username) {
        if(!existsByUsername(username)){
            throw new EntityNotFoundException("Driver with specified username does not exists");
        }
        return driverRepository.findByUsername(username);
    }

    @Override
    public Driver createDriver(DriverCreateRequestDto request) {
        if(userService.existsByUsername(request.getUsername())){
            throw new UsernameAlreadyExistsException("User with specified username already exists");
        }
        if(userService.existsByEmail(request.getEmail())){
            throw new EmailAlreadyExistsException("User with specified email already exists");
        }
        VehicleType vehicleType = vehicleTypeService.findById(request.getVehicleTypeId());
        Vehicle vehicle = new Vehicle();
        vehicle.setCapacity(request.getCapacity());
        vehicle.setBabyTransport(request.getBabyTransport());
        vehicle.setPetTransport(request.getPetTransport());
        vehicle.setVehicleType(vehicleType);
        Driver d = new Driver();
        d.setName(request.getName());
        d.setLastName(request.getLastName());
        List<Role> roles = new ArrayList<>();
        roles.add(roleService.findRoleByName("ROLE_DRIVER"));
        d.setRoles(roles);
        d.setPassword(passwordEncoder.encode(request.getPassword()));
        d.setEmail(request.getEmail());
        d.setUsername(request.getUsername());
        d.setPhone(request.getPhone());
        d.setAddress(request.getAddress());
        d.setVehicle(vehicleService.createVehicle(vehicle));
        driverRepository.save(d);
        return d;
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

    @Override
    public boolean existsByEmail(String email) {
        return driverRepository.existsByEmail(email);
    }
}
