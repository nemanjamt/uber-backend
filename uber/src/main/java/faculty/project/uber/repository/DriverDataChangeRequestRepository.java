package faculty.project.uber.repository;

import faculty.project.uber.model.others.DriverDataChangeRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverDataChangeRequestRepository extends JpaRepository<DriverDataChangeRequest, Long> {

    boolean existsByDriverId(Long driverId);
    DriverDataChangeRequest findByDriverId(Long driverId);
}
