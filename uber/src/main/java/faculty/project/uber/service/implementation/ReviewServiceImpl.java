package faculty.project.uber.service.implementation;

import faculty.project.uber.dto.review.request.ReviewRequest;
import faculty.project.uber.dto.review.response.ReviewResponse;
import faculty.project.uber.model.others.Review;
import faculty.project.uber.model.others.Ride;
import faculty.project.uber.model.users.Client;
import faculty.project.uber.repository.ReviewRepository;
import faculty.project.uber.service.ClientService;
import faculty.project.uber.service.ReviewService;
import faculty.project.uber.service.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    RideService rideService;
    @Autowired
    ClientService clientService;
    @Override
    public List<ReviewResponse> findByRideId(Long rideId) {
        return reviewRepository.findByRideId(rideId).stream().map(r -> new ReviewResponse((r))).toList();
    }

    @Override
    public void createReview(ReviewRequest request) {
        Client c = clientService.findById(request.getClientId());
        Ride ride = rideService.findById(request.getRideId());
        Review review = new Review();
        review.setGrade(request.getGrade());
        review.setComment(request.getComment());
        review.setRide(ride);
        review.setClient(c);
        reviewRepository.save(review);
    }
}
