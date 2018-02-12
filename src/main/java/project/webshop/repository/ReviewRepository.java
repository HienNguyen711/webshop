package project.webshop.repository;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import project.webshop.model.entity.Review;

@Service("reviewRepository")
public interface ReviewRepository extends CrudRepository<Review, Long> {
}
