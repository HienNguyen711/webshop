package project.webshop.service;

import org.springframework.stereotype.Service;
import project.webshop.model.dto.ReviewDto;

@Service
public interface ReviewService {

    ReviewDto addReview(Long userId, ReviewDto reviewDto, Long productId) throws Exception;

    void deleteReview(Long userId, Long reviewId) throws Exception;
}
