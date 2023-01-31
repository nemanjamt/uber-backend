package faculty.project.uber.controller;

import faculty.project.uber.dto.driver.request.DriverCreateRequestDto;
import faculty.project.uber.dto.user.request.ChangeUserDataRequest;
import faculty.project.uber.service.DriverDataChangeRequestService;
import faculty.project.uber.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/driver")
public class DriverController {

    DriverDataChangeRequestService driverDataChangeRequestService;

    DriverService driverService;
    @Autowired
    public DriverController(DriverDataChangeRequestService driverDataChangeRequestService, DriverService driverService){
        this.driverDataChangeRequestService = driverDataChangeRequestService;
        this.driverService = driverService;
    }

    @GetMapping("/all")
    public ResponseEntity findAll(){
        return new ResponseEntity(driverService.findAll(), HttpStatus.OK);
    }
    @PostMapping("/dataChangeRequest/{driverId}")
    ResponseEntity createRequest(@PathVariable Long driverId, @RequestBody ChangeUserDataRequest request){
        driverDataChangeRequestService.createDriverDataChangeRequest(driverId, request);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/dataChangeRequest/{id}")
    ResponseEntity changeRequest(@PathVariable Long id, @RequestBody ChangeUserDataRequest request){
        driverDataChangeRequestService.changeExistingRequest(id,request);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PreAuthorize("#driverId == authentication.principal.id")
    @GetMapping("/dataChangeRequest/{driverId}")
    ResponseEntity findRequest(@PathVariable Long driverId){
        return new ResponseEntity(driverDataChangeRequestService.findByDriver(driverId),HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/dataChangeRequests")
    ResponseEntity findAllRequests(){
        return new ResponseEntity(driverDataChangeRequestService.findAllRequests(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping()
    ResponseEntity createDriver(@Valid @RequestBody DriverCreateRequestDto requestDto){
        driverService.createDriver(requestDto);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/approveDataChangeRequest/{id}")
    ResponseEntity approveRequest(@PathVariable Long id){
        driverDataChangeRequestService.approveDataChangeRequest(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/rejectDataChangeRequest/{id}")
    ResponseEntity rejectRequest(@PathVariable Long id){
        driverDataChangeRequestService.rejectDataChangeRequest(id);
        return new ResponseEntity(HttpStatus.OK);
    }


    @PutMapping("/deactivate/{id}")
    ResponseEntity deactivateDriver(@PathVariable Long id){
        driverService.deactivateDriver(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/activate/{id}")
    ResponseEntity activateDriver(@PathVariable Long id){
        driverService.activateDriver(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
