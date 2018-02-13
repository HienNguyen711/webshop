package project.webshop.service;


import org.springframework.stereotype.Service;
import project.webshop.model.dto.ProductDto;
import project.webshop.model.dto.ReviewDto;

import java.util.List;
import java.util.Set;


@Service
public interface ProductService {
    ProductDto editProduct(ProductDto productDto) throws Exception;

    ProductDto addProduct(ProductDto productDto) throws Exception;

    void deleteProduct(Long id) throws Exception;

    ProductDto getProduct(Long productId, Long userId) throws Exception;

    List<ProductDto> getProducts(int offset, int limit, long categoryId) throws Exception;

    Set<ReviewDto> getProductReviews(Long productId) throws Exception;

    ProductDto getProductSpecifications(Long productId) throws Exception;

    ProductDto addCategoryToProduct(Long productId, Long categoryId) throws Exception;

    void deleteCategoryFromProduct(Long productId) throws Exception;
}