package faculty.project.uber.service.implementation;

import faculty.project.uber.dto.report.request.RideReportRequest;
import faculty.project.uber.dto.report.response.ReportResponse;
import faculty.project.uber.dto.report.response.ReportSummaryResponse;
import faculty.project.uber.dto.ride.response.RideBasicResponse;
import faculty.project.uber.dto.ride.response.RideDetailedResponse;
import faculty.project.uber.model.others.Ride;
import faculty.project.uber.model.users.Client;
import faculty.project.uber.model.users.Driver;
import faculty.project.uber.repository.RideRepository;
import faculty.project.uber.service.ClientService;
import faculty.project.uber.service.DriverService;
import faculty.project.uber.service.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RideServiceImpl implements RideService {
    @Autowired
    RideRepository rideRepository;

    @Autowired
    ClientService clientService;

    @Autowired
    DriverService driverService;


    @Override
    public List<RideBasicResponse> findByClient(Long clientId) {
        return rideRepository.findByClientId(clientId).stream().map(r -> new RideBasicResponse(r)).toList();
    }

    @Override
    public List<RideBasicResponse> findByDriver(Long driverId) {
        return rideRepository.findByDriverId(driverId).stream().map(r -> new RideBasicResponse(r)).toList();
    }

    @Override
    public List<RideBasicResponse> findByUsername(String username) {
        return rideRepository.findByUsersUsername(username).stream().map(r -> new RideBasicResponse(r)).toList();
    }

    @Override
    public Ride findById(Long id) {
        if(!rideRepository.existsById(id)){
            throw new EntityNotFoundException("RIDE WITH SPECIFIED ID DOES NOT EXISTS");
        }
        return rideRepository.findById(id).get();
    }
    @Override
    public RideDetailedResponse findDetailedRideById(Long id) {

        Ride r = findById(id);
        RideDetailedResponse detailedRide = new RideDetailedResponse(r);
        return detailedRide;
    }



    @Override
    public List<ReportResponse> findReportClient(Long clientId, RideReportRequest request) {
        List<ReportResponse> res=  rideRepository.findRidesGroupByDate(request.getStartDate().atStartOfDay(),request.getEndDate().atStartOfDay(), clientId);
        return res.stream().sorted(Comparator.comparing(ReportResponse::getDate)).collect(Collectors.toList());
    }

    @Override
    public List<ReportResponse> findReportDriver(Long driverId, RideReportRequest request) {
        List<ReportResponse> res = rideRepository.findRidesGroupByDateAndDriver(request.getStartDate().atStartOfDay(),request.getEndDate().atStartOfDay(), driverId);

        return res.stream().sorted(Comparator.comparing(ReportResponse::getDate)).collect(Collectors.toList());

    }

    @Override
    public List<ReportResponse> findReportAdmin(RideReportRequest request) {

        List<ReportResponse> res = rideRepository.findRidesGroupByDate(request.getStartDate().atStartOfDay(),request.getEndDate().atStartOfDay());
        return res.stream().sorted(Comparator.comparing(ReportResponse::getDate)).collect(Collectors.toList());
    }

    @Override
    public List<ReportResponse> findReportByUsername(RideReportRequest request, String username) {
        if(!driverService.existsByUsername(username) && !clientService.existsByUsername(username)){
            throw new EntityNotFoundException("ENTITY WITH SPECIFIED USERNAME DOES NOT EXISTS");
        }

        if(driverService.existsByUsername(username)){
            Driver d = driverService.findByUsername(username);
            return findReportDriver(d.getId(), request);
        }

        Client c = clientService.findByUsername(username);
        return findReportClient(c.getId(),request);

    }

    @Override
    public ReportSummaryResponse findReportSummaryAdmin(RideReportRequest request) {
        return rideRepository.findTotalAndAverage(request.getStartDate().atStartOfDay(), request.getEndDate().atStartOfDay());
    }

    @Override
    public ReportSummaryResponse findReportSummaryClient(RideReportRequest request, Long clientId) {
        return rideRepository.findTotalAndAverageClient(request.getStartDate().atStartOfDay(), request.getEndDate().atStartOfDay(), clientId);
    }

    @Override
    public ReportSummaryResponse findReportSummaryDriver(RideReportRequest request, Long driverId) {
        return rideRepository.findTotalAndAverageDriver(request.getStartDate().atStartOfDay(), request.getEndDate().atStartOfDay(), driverId);
    }

    @Override
    public ReportSummaryResponse findReportSummaryByUsername(RideReportRequest request, String username) {
        if(!driverService.existsByUsername(username) && !clientService.existsByUsername(username)){
            throw new EntityNotFoundException("ENTITY WITH SPECIFIED USERNAME DOES NOT EXISTS");
        }

        if(driverService.existsByUsername(username)){
            Driver d = driverService.findByUsername(username);
            return findReportSummaryDriver( request,d.getId());
        }

        Client c = clientService.findByUsername(username);
        return findReportSummaryClient(request,c.getId());
    }
}
