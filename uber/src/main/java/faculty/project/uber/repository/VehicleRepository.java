package faculty.project.uber.repository;

import faculty.project.uber.model.others.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle,Long> {
}
