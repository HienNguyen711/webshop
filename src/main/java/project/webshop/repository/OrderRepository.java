package project.webshop.repository;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import project.webshop.model.entity.Order;

@Service("orderRepository")
public interface OrderRepository extends CrudRepository<Order, Long> {
}
