package faculty.project.uber.dto.client.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClientRegistrationRequest {
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    @NotBlank
    private String lastName;
    @NotNull
    private String address;
    @NotNull
    private String phone;
    @NotNull
    private String username;
    @NotNull
    @Email
    private String email;
    @NotNull
    @NotBlank
    private String password;
}
