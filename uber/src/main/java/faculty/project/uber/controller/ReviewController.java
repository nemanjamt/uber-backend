package faculty.project.uber.controller;

import faculty.project.uber.dto.review.request.ReviewRequest;
import faculty.project.uber.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/review")
public class ReviewController {
    @Autowired
    ReviewService reviewService;

    @GetMapping("/findByRide/{rideId}")
    ResponseEntity findByRideId(@PathVariable Long rideId){

        return new ResponseEntity(reviewService.findByRideId(rideId), HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity createReview(@RequestBody ReviewRequest request){
        reviewService.createReview(request);
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
