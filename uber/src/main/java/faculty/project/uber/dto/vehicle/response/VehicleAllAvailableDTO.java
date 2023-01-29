package faculty.project.uber.dto.vehicle.response;

import faculty.project.uber.model.others.Coordinates;
import faculty.project.uber.model.others.Route;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VehicleAllAvailableDTO {
    private Long id;

    private String licencePlate;

    private String vehicleType;

    private boolean occupied;

    private Coordinates location;

    private Route route;
}
