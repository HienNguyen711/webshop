package project.webshop.service;

import org.springframework.stereotype.Service;
import project.webshop.model.dto.ProductDto;

import java.util.Set;

@Service
public interface CartService {
    ProductDto addProductToCart(Long userId, Long productId) throws Exception;

    void deleteProductFromCart(Long userId, Long productId) throws Exception;

    Set<ProductDto> getProductsFromCart(Long userId) throws Exception;
}
