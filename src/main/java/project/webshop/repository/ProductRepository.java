package project.webshop.repository;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import project.webshop.model.entity.Product;


@Service("productRepository")
public interface ProductRepository extends CrudRepository<Product, Long> {
}
