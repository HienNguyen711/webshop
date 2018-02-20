package project.webshop.repository;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import project.webshop.model.entity.Order;
import project.webshop.model.entity.payment.Currency;

import java.util.List;

@Service("orderRepository")
public interface OrderRepository extends CrudRepository<Order, Long> {
    // find by currency
    List<Order> findByCurrency(final Currency currency);
    
}
