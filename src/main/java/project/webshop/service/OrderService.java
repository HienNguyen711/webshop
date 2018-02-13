package project.webshop.service;

import org.springframework.stereotype.Service;
import project.webshop.model.dto.OrderDto;
import project.webshop.model.dto.ProductListDto;

import java.util.Set;

@Service
public interface OrderService {
    // make order
    OrderDto makeOrder(Set<ProductListDto> productDtos, Long userId) throws Exception;

    // payorder
    void payOrder(Long userId, Long orderId) throws Exception;

    // get list orders by user id
    Set<OrderDto> getOrders(Long userId) throws Exception;

    // get one order
    OrderDto getOrder(Long userId, Long orderId) throws Exception;

    // cancel order
    void cancelOrder(Long userId, Long orderId) throws Exception;
}
