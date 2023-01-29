package faculty.project.uber.controller;

import faculty.project.uber.dto.report.request.RideReportRequest;
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


    @PostMapping("/reportClient/{clientId}")
    ResponseEntity getReportClient(@PathVariable Long clientId, @RequestBody RideReportRequest request){
        return new ResponseEntity(rideService.findReportClient(clientId,request),HttpStatus.OK);
    }

    @PostMapping("/reportDriver/{driverId}")
    ResponseEntity getReportDriver(@PathVariable Long driverId, @RequestBody RideReportRequest request){
        return new ResponseEntity(rideService.findReportDriver(driverId,request),HttpStatus.OK);
    }

    @PostMapping("/reportAdmin")
    ResponseEntity getReportAdmin( @RequestBody RideReportRequest request){
        return new ResponseEntity(rideService.findReportAdmin(request), HttpStatus.OK);
    }



    @PostMapping("/reportSummaryClient/{clientId}")
    ResponseEntity getReportSummaryClient(@PathVariable Long clientId, @RequestBody RideReportRequest request){
        return new ResponseEntity(rideService.findReportSummaryClient(request,clientId),HttpStatus.OK);
    }

    @PostMapping("/reportSummaryDriver/{driverId}")
    ResponseEntity getReportSummaryDriver(@PathVariable Long driverId, @RequestBody RideReportRequest request){
        return new ResponseEntity(rideService.findReportSummaryDriver(request,driverId),HttpStatus.OK);
    }

    @PostMapping("/reportSummaryAdmin")
    ResponseEntity getReportSummaryAdmin( @RequestBody RideReportRequest request){
        return new ResponseEntity(rideService.findReportSummaryAdmin(request), HttpStatus.OK);
    }

    @PostMapping("/reportSummaryByUsername/{username}")
    ResponseEntity getReportSummaryByUsername(@PathVariable String username, @RequestBody RideReportRequest request){
        return new ResponseEntity(rideService.findReportSummaryByUsername(request,username),HttpStatus.OK);
    }

    @PostMapping("/reportByUsername/{username}")
    ResponseEntity getReportByUsername(@PathVariable String username, @RequestBody RideReportRequest request){
        return new ResponseEntity(rideService.findReportByUsername(request,username),HttpStatus.OK);
    }





}
