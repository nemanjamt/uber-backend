package faculty.project.uber.model.users;

import faculty.project.uber.model.others.Payment;
import faculty.project.uber.model.others.Review;
import faculty.project.uber.model.others.Ride;
import faculty.project.uber.model.others.Route;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="clients")
public class Client extends User{

    @OneToOne
    private Route favouriteRoute;
    private Boolean blocked;
    private Boolean activated;
    @OneToMany
    private List<Payment> payments;
    @ManyToMany
    @JoinTable(name = "CLIENTS_RIDES",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "ride_id"))
    private List<Ride> rides;

    @OneToMany(mappedBy = "client")
    List<Review> reviews;
}