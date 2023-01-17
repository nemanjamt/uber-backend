package faculty.project.uber.model.users;

import faculty.project.uber.model.others.DriverDataChangeRequest;
import faculty.project.uber.model.others.Rejection;
import faculty.project.uber.model.others.Ride;
import faculty.project.uber.model.others.Vehicle;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="drivers")
public class Driver extends User{

    @OneToOne
    private Vehicle vehicle;
    @OneToMany
    private List<Ride> rides;
    private Boolean active;
    private Boolean available;
    @OneToMany
    private List<Rejection> rejections;

    @OneToOne
    DriverDataChangeRequest driverDataChangeRequest;
}
