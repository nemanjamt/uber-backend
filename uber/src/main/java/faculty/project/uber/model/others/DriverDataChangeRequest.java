package faculty.project.uber.model.others;

import faculty.project.uber.model.users.Driver;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name="driver_data_change_request")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class DriverDataChangeRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String lastName;
    private String address;
    private String phone;
    private String email;
    private String username;
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    Driver driver;
}
