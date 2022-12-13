package faculty.project.uber.dto.user.response;

import faculty.project.uber.model.users.User;
import lombok.Data;

@Data
public class ReadUserResponse {

    private Long id;
    private String name;
    private String lastName;
    private String username;
    private String email;
    private String phone;
    private String address;
    private Long photoId;
    public ReadUserResponse(User u){
        this.id = u.getId();
        this.name = u.getName();
        this.lastName = u.getLastName();
        this.username = u.getUsername();
        this.email =  u.getEmail();
        this.address = u.getAddress();
        this.phone = u.getPhone();
        this.photoId = u.getPhoto() == null ? null :u.getPhoto().getId();
    }
}
