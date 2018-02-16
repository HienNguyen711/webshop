package project.webshop.repository;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import project.webshop.model.entity.Category;

@Service("categoryRepository")
//@SuppressWarnings("unused")
public interface CategoryRepository extends CrudRepository<Category, Long> {
}
