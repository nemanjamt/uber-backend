package faculty.project.uber.model.others;

import faculty.project.uber.model.enums.RideStatus;
import faculty.project.uber.model.users.Client;
import faculty.project.uber.model.users.Driver;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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
    private LocalDateTime ends;
    private Double total;
    private LocalTime estimatedTime;
    private RideStatus status;
    private Boolean babyTransport;
    private Boolean petTransport;
    private Boolean splitFare;
    private String comment;
    private String name;
    @OneToOne
    private Route route;
    @OneToMany(mappedBy = "ride")
    private List<Review> reviews;
    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(name = "CLIENTS_RIDES",
            joinColumns = @JoinColumn(name = "ride_id"),
            inverseJoinColumns = @JoinColumn(name = "client_id"))
    private List<Client> clients;
    @OneToMany
    private List<Payment> payments;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;

}
