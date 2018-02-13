package project.webshop.service.impl;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import project.webshop.model.dto.ReviewDto;
import project.webshop.model.entity.Product;
import project.webshop.model.entity.Review;
import project.webshop.model.entity.user.User;
import project.webshop.repository.ProductRepository;
import project.webshop.repository.ReviewRepository;
import project.webshop.repository.UserRepository;
import project.webshop.service.ReviewService;
import project.webshop.utils.Constants;
import project.webshop.utils.Converter;

import java.util.List;


@Component
@Transactional
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    @Qualifier("productRepository")
    private ProductRepository productRepository;

    @Autowired
    @Qualifier("userRepository")
    private UserRepository userRepository;

    @Autowired
    @Qualifier("reviewRepository")
    private ReviewRepository reviewRepository;


    @Override
    public ReviewDto addReview(Long userId, @NonNull ReviewDto reviewDto, Long productId) throws Exception {
        User user = userRepository.findOne(userId);
        if (user == null) {
            throw new Exception(Constants.MESSAGE_NOT_FOUND_USER);
        }
        Product product = productRepository.findOne(productId);
        if (product == null) {
            throw new Exception(Constants.MESSAGE_NOT_FOUND_PRODUCT);
        }
        Review review = Converter.toReview(reviewDto);
        review.setUser(user);
        review.setProduct(product);
        reviewRepository.save(review);
        reviewDto.setId(review.getId());
        reviewDto.setAuthor(user.getName());
        return reviewDto;
    }

    @Override
    public void deleteReview(Long userId, Long reviewId) throws Exception {
        User user = userRepository.findOne(userId);
        if (user == null) {
            throw new Exception(Constants.MESSAGE_NOT_FOUND_USER);
        }
        List<Review> reviews = user.getProductReviews();
        if (reviews == null) {
            throw new Exception("User has not reviews");
        }
        Review searchedReview = null;
        for (Review existingReview : reviews) {
            if (existingReview.getId().equals(reviewId)) {
                searchedReview = existingReview;
                break;
            }
        }
        if (searchedReview == null) {
            throw new Exception(Constants.MESSAGE_NOT_FOUND_REVIEW);
        }
        reviewRepository.delete(searchedReview.getId());
    }

}

