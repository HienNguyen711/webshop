package project.webshop.repository;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import project.webshop.model.entity.Category;

@Service("categoryRepository")
public interface CategoryRepository extends CrudRepository<Category, Long> {
}
