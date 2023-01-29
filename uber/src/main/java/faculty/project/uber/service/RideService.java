package faculty.project.uber.service;

import faculty.project.uber.dto.report.request.RideReportRequest;
import faculty.project.uber.dto.report.response.ReportResponse;
import faculty.project.uber.dto.report.response.ReportSummaryResponse;
import faculty.project.uber.dto.ride.response.RideBasicResponse;
import faculty.project.uber.dto.ride.response.RideDetailedResponse;
import faculty.project.uber.model.others.Ride;

import java.util.List;

public interface RideService {
    List<RideBasicResponse> findByClient(Long clientId);

    List<RideBasicResponse> findByDriver(Long driverId);
    List<RideBasicResponse> findByUsername(String username);
    Ride findById(Long id);
    RideDetailedResponse findDetailedRideById(Long id);

    List<ReportResponse> findReportClient(Long clientId, RideReportRequest request);

    List<ReportResponse> findReportDriver(Long driverId, RideReportRequest request);
    List<ReportResponse> findReportAdmin(RideReportRequest request);

    List<ReportResponse> findReportByUsername(RideReportRequest request, String username);

    ReportSummaryResponse findReportSummaryAdmin(RideReportRequest request);
    ReportSummaryResponse findReportSummaryClient(RideReportRequest request, Long clientId);
    ReportSummaryResponse findReportSummaryDriver(RideReportRequest request, Long driverId);
    ReportSummaryResponse findReportSummaryByUsername(RideReportRequest request, String username);
}
