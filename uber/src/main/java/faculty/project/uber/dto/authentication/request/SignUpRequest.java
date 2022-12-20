package faculty.project.uber.dto.authentication.request;

import lombok.Data;

@Data
public class SignUpRequest {
    private String name;
    private String email;
}
