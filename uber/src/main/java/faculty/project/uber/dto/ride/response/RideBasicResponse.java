package faculty.project.uber.dto.ride.response;

import faculty.project.uber.model.others.Ride;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class RideBasicResponse {
    private String name;
    private Long id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Double price;
    public RideBasicResponse(Ride r){
        this.name = r.getName();
        this.id = r.getId();
        this.startDate = r.getStart();
        this.endDate = r.getEnds();
        this.price = r.getTotal();
    }
}
