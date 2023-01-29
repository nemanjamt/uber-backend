package faculty.project.uber.repository;

import faculty.project.uber.dto.report.response.ReportResponse;
import faculty.project.uber.dto.report.response.ReportSummaryResponse;
import faculty.project.uber.model.others.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface RideRepository extends JpaRepository<Ride,Long> {
    @Query("SELECT r FROM Ride r JOIN r.clients c WHERE c.id = :clientId AND r.status = 4 ORDER BY r.ends DESC")
    List<Ride> findByClientId(Long clientId);

    @Query("SELECT r FROM Ride r  WHERE r.driver.id = :driverId AND r.status = 4 ORDER BY r.ends DESC")
    List<Ride> findByDriverId(Long driverId);

    @Query("SELECT r FROM Ride r WHERE r.status = 4 AND r.id IN (SELECT r.id FROM Ride r JOIN r.clients c WHERE c.username = :username OR r.driver.username = :username) ORDER BY r.ends DESC")
    List<Ride> findByUsersUsername(String username);

    @Query("SELECT new faculty.project.uber.dto.report.response.ReportResponse(CAST(r.ends AS date) , COUNT(r) , SUM(r.route.length) , SUM(r.total) ) FROM Ride r WHERE r.status = 4 AND r.ends BETWEEN :startDate AND :endDate GROUP BY CAST(r.ends AS date) ")
    List<ReportResponse> findRidesGroupByDate(LocalDateTime startDate, LocalDateTime endDate);

    @Query("SELECT new faculty.project.uber.dto.report.response.ReportResponse(CAST(r.ends AS date) , COUNT(r) , SUM(r.route.length) , SUM(r.total) ) FROM Ride r JOIN r.clients c WHERE r.status = 4 AND r.ends BETWEEN :startDate AND :endDate AND c.id = :clientId GROUP BY CAST(r.ends AS date) ")
    List<ReportResponse> findRidesGroupByDate(LocalDateTime startDate, LocalDateTime endDate, Long clientId);


    @Query("SELECT new faculty.project.uber.dto.report.response.ReportResponse(CAST(r.ends AS date) , COUNT(r) , SUM(r.route.length) , SUM(r.total) ) FROM Ride r WHERE r.status = 4 AND  r.driver.id = :driverId AND r.ends BETWEEN :startDate AND :endDate GROUP BY CAST(r.ends AS date) ")
    List<ReportResponse> findRidesGroupByDateAndDriver(LocalDateTime startDate, LocalDateTime endDate, Long driverId);


    //    @Query("SELECT new taxi.system.uberbackend.dto.report.response.ReportSummaryResponse(COUNT(r), DAY(date_trunc('day', :endDate) - date_trunc('day', :startDate)) , COALESCE(AVG(r.total),0), COALESCE(SUM(r.total),0), COALESCE(AVG(r.route.length),0), COALESCE(SUM(r.route.length),0)) FROM Ride r WHERE r.status = 4 AND r.ends BETWEEN :startDate AND :endDate")
    @Query("SELECT new faculty.project.uber.dto.report.response.ReportSummaryResponse(COUNT(r), DAY(date_trunc('day', cast(:endDate as date)) - date_trunc('day', cast(:startDate as date))), COALESCE(AVG(r.total),0), COALESCE(SUM(r.total),0), COALESCE(AVG(r.route.length),0), COALESCE(SUM(r.route.length),0)) FROM Ride r WHERE r.status = 4 AND r.ends BETWEEN :startDate AND :endDate")
    ReportSummaryResponse findTotalAndAverage(LocalDateTime startDate, LocalDateTime endDate);

    @Query("SELECT new faculty.project.uber.dto.report.response.ReportSummaryResponse(COUNT(r), DAY(date_trunc('day', cast(:endDate as date)) - date_trunc('day', cast(:startDate as date))) , COALESCE(AVG(r.total),0), COALESCE(SUM(r.total),0), COALESCE(AVG(r.route.length),0), COALESCE(SUM(r.route.length),0)) FROM Ride r JOIN r.clients c WHERE r.status = 4 AND c.id=:clientId AND  r.ends BETWEEN :startDate AND :endDate")
    ReportSummaryResponse findTotalAndAverageClient(LocalDateTime startDate, LocalDateTime endDate, Long clientId);

    @Query("SELECT new faculty.project.uber.dto.report.response.ReportSummaryResponse(COUNT(r), DAY(date_trunc('day', cast(:endDate as date)) - date_trunc('day', cast(:startDate as date))) , COALESCE(AVG(r.total),0), COALESCE(SUM(r.total),0), COALESCE(AVG(r.route.length),0), COALESCE(SUM(r.route.length),0)) FROM Ride r  WHERE r.status = 4 AND r.driver.id = :driverId AND r.ends BETWEEN :startDate AND :endDate")
    ReportSummaryResponse findTotalAndAverageDriver(LocalDateTime startDate, LocalDateTime endDate, Long driverId);
}
