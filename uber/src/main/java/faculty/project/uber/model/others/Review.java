package faculty.project.uber.model.others;

import faculty.project.uber.model.users.Client;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int grade;
    private String comment;
    @ManyToOne
    @JoinColumn(name = "ride_id")
    private Ride ride;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
}
