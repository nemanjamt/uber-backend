package faculty.project.uber.repository;

import faculty.project.uber.model.others.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query(value = "SELECT * FROM Messages m WHERE m.receiver_id = ?1  OR m.sender_id = ?1  ORDER BY m.time", nativeQuery = true)
    List<Message> findByUsers(Long userId);
}
