package faculty.project.uber.dto.review.request;

import lombok.Data;

@Data
public class ReviewRequest {
    String comment;
    Integer grade;
    Long rideId;
    Long clientId;
}
