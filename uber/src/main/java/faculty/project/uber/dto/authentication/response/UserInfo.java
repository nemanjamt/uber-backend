package faculty.project.uber.dto.authentication.response;

import lombok.Value;

import java.util.List;

@Value
public class UserInfo {
    private String id, name, email;
    private List<String> roles;
}
