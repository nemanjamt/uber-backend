package faculty.project.uber.model.others;

import faculty.project.uber.model.enums.RideStatus;
import faculty.project.uber.model.users.Client;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ride {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime start;
    private LocalDateTime end;
    private float total;
    private LocalTime estimatedTime;
    private RideStatus status;
    private Boolean babyTransport;
    private Boolean petTransport;
    private Boolean splitFare;
    private String comment;
    @OneToOne
    private Route route;
    @OneToMany
    private List<Review> reviews;
    @ManyToMany
    private List<Client> clients;
    @OneToMany
    private List<Payment> payments;

}
