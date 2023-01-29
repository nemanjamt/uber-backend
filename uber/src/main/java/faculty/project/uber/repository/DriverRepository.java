package faculty.project.uber.repository;

import faculty.project.uber.model.users.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<Driver,Long> {
    boolean existsByUsername(String username);
    Driver findByUsername(String username);
}
