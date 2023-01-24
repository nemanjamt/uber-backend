package faculty.project.uber.repository;

import faculty.project.uber.model.users.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);

    Client findByUsername(String username);
}
