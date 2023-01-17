package faculty.project.uber.dto.driver.request;


import faculty.project.uber.model.others.DriverDataChangeRequest;
import faculty.project.uber.model.users.Driver;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DriverDataChangeRequestDto {
    private Long id;

    private String name;
    private String lastName;
    private String address;
    private String phone;
    private String username;
    private String email;


    public DriverDataChangeRequestDto(DriverDataChangeRequest req, Driver d){
        this.id = req.getId();
        this.name = req.getName();
        this.lastName = req.getLastName();
        this.address = req.getAddress();
        this.phone = req.getPhone();
        this.username = d.getUsername();
        this.email = d.getEmail();
    }


}
