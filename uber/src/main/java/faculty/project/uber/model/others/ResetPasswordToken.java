package faculty.project.uber.model.others;

import faculty.project.uber.model.users.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
@Entity
@Getter
@Setter
public class ResetPasswordToken {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String token;

    @ManyToOne
    private User user;
}
