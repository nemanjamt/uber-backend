package faculty.project.uber.repository;

import faculty.project.uber.model.others.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RideRepository extends JpaRepository<Ride,Long> {
    @Query("SELECT r FROM Ride r JOIN r.clients c WHERE c.id = :clientId AND r.status = 4 ORDER BY r.ends DESC")
    List<Ride> findByClientId(Long clientId);

    @Query("SELECT r FROM Ride r  WHERE r.driver.id = :driverId AND r.status = 4 ORDER BY r.ends DESC")
    List<Ride> findByDriverId(Long driverId);

    @Query("SELECT r FROM Ride r WHERE r.status = 4 AND r.id IN (SELECT r.id FROM Ride r JOIN r.clients c WHERE c.username = :username OR r.driver.username = :username) ORDER BY r.ends DESC")
    List<Ride> findByUsersUsername(String username);
}
