package faculty.project.uber.dto.coordinates.response;

import faculty.project.uber.model.others.Coordinates;
import lombok.Data;

@Data
public class CoordinatesResponseDTO {
    private String longitude;
    private String latitude;
    public CoordinatesResponseDTO(Coordinates c){
        this.longitude = c.getLongitude();
        this.latitude = c.getLatitude();
    }

}
