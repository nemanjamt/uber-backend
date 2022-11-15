package faculty.project.uber.model.others;

import faculty.project.uber.model.enums.MessageType;
import faculty.project.uber.model.users.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;
    private LocalDateTime time;
    private MessageType type;
    @ManyToOne
    private Ride ride;
    @ManyToOne
    private User sender;
    @ManyToOne
    private User receiver;
}
