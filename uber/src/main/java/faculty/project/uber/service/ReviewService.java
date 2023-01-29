package faculty.project.uber.service;

import faculty.project.uber.dto.review.request.ReviewRequest;
import faculty.project.uber.dto.review.response.ReviewResponse;

import java.util.List;

public interface ReviewService {
    List<ReviewResponse> findByRideId(Long rideId);

    void createReview(ReviewRequest request);
}
