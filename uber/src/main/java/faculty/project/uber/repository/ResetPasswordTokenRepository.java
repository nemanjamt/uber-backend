package faculty.project.uber.repository;

import faculty.project.uber.model.others.ResetPasswordToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResetPasswordTokenRepository extends JpaRepository<ResetPasswordToken, Long> {
    boolean existsByToken(String token);
    ResetPasswordToken findByToken(String token);
}
