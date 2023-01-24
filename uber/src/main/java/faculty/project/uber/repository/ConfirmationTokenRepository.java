package faculty.project.uber.repository;

import faculty.project.uber.model.others.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {
    ConfirmationToken findByConfirmationToken(String confirmationToken);
    boolean existsByConfirmationToken(String confirmationToken);
}
