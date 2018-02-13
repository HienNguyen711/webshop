package project.webshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.webshop.model.dto.ReviewDto;
import project.webshop.service.ReviewService;

@RestController
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    // add review
    @RequestMapping(value = "user/{userId}/add/reviews/{productId}", method = RequestMethod.POST)
    public ResponseEntity<?> addReview(@RequestBody ReviewDto reviewDto,
                                       @PathVariable Long userId,
                                       @PathVariable Long productId,
                                       @RequestHeader(name = "Authorization") String token) throws Exception {
        ReviewDto existingReviewDto;
        try {
            existingReviewDto = reviewService.addReview(userId, reviewDto, productId);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(existingReviewDto, HttpStatus.OK);
    }



    // delete review
    @RequestMapping(value = "user/{userId}/delete/reviews/{reviewId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteReview(@PathVariable Long userId,
                                          @PathVariable Long reviewId,
                                          @RequestHeader(name = "Authorization") String token) throws Exception {
        try {
            reviewService.deleteReview(userId, reviewId);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok().build();
    }
}
