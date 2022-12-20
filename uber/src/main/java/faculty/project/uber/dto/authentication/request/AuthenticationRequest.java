package faculty.project.uber.dto.authentication.request;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class AuthenticationRequest {
    @NotNull(message = "username must not be null")
    @NotBlank(message = "username must not be blank")
    private String username;
    @NotNull(message = "password must not be null")
    @NotBlank(message = "password must not be blank")
    private String password;
}
