package faculty.project.uber.controller;

import faculty.project.uber.service.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ride")
public class RideController {

    @Autowired
    RideService rideService;
    @GetMapping("/findByClient/{clientId}")
    ResponseEntity findRidesByClient(@PathVariable Long clientId){
        return   new ResponseEntity(rideService.findByClient(clientId), HttpStatus.OK);
    }


    @GetMapping("/findByDriver/{driverId}")
    ResponseEntity findRidesByDriver(@PathVariable Long driverId){
        return new ResponseEntity(rideService.findByDriver(driverId), HttpStatus.OK);
    }

    @GetMapping("/findByUsername/{username}")
    ResponseEntity findRidesByUsername(@PathVariable String username){
        return new ResponseEntity(rideService.findByUsername(username), HttpStatus.OK);
    }

    @GetMapping("/findDetailedRide/{rideId}")
    ResponseEntity findDetailedRide(@PathVariable  Long rideId){
        return new ResponseEntity(rideService.findDetailedRideById(rideId), HttpStatus.OK);
    }






}
