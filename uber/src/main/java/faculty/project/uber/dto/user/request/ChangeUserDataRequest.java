package faculty.project.uber.dto.user.request;

import lombok.Data;

@Data
public class ChangeUserDataRequest {
    private String name;
    private String lastName;
    private String phone;
    private String address;
}
