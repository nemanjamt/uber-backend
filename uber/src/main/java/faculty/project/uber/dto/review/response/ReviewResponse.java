package faculty.project.uber.dto.review.response;

import faculty.project.uber.dto.user.response.ReadUserResponse;
import faculty.project.uber.model.others.Review;
import lombok.Data;

@Data
public class ReviewResponse {
    private int grade;
    private String comment;
    private Long id;
    private ReadUserResponse client;
    public ReviewResponse(Review r){
        this.grade = r.getGrade();
        this.comment = r.getComment();
        this.id = r.getId();
        this.client = new ReadUserResponse(r.getClient());
    }

}
