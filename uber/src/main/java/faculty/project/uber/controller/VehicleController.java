package faculty.project.uber.controller;

import faculty.project.uber.dto.vehicle.response.VehicleAllAvailableDTO;
import faculty.project.uber.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/all/vehicle")
public class VehicleController {

    private final VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService){
        this.vehicleService = vehicleService;
    }

    @GetMapping("all")
    public ResponseEntity<List<VehicleAllAvailableDTO>> getAllVehicle(){
        return new ResponseEntity<>(vehicleService.findAllAvailableCars(), HttpStatus.OK);
    }
}
