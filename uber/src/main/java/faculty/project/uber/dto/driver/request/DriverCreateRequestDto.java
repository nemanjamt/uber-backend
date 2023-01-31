package faculty.project.uber.dto.driver.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
public class DriverCreateRequestDto {
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    @NotBlank
    private String lastName;
    @NotNull
    @NotBlank
    private String address;
    @NotNull
    @NotBlank
    private String phone;
    @NotNull
    @NotBlank
    private String username;
    @NotNull
    @Email
    private String email;

    @NotNull
    private Integer capacity;
    @NotNull
    private Boolean babyTransport;
    @NotNull
    private Boolean petTransport;
    @NotNull
    @NotBlank
    private String password;
    @NotNull
    private Long vehicleTypeId;
}
