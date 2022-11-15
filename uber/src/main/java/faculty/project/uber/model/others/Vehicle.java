package faculty.project.uber.model.others;

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
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String licencePlate;
    private Integer capacity;
    private Boolean babyTransport;
    private Boolean petTransport;
    private Boolean occupied;
    @OneToOne
    private Coordinates location;
    @OneToOne
    private VehicleType vehicleType;
}
