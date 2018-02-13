package project.webshop.service;


import org.springframework.stereotype.Service;
import project.webshop.model.dto.ProductDto;

import java.util.Set;

@Service
public interface WishListService {
    // add product to wishlist : productId, userid
    ProductDto addProductToWishList(Long userId, Long productId) throws Exception;

    // delete product from wishlist
    // user id, product id
    void deleteProductFromWishList(Long userId, Long productId) throws Exception;

    // get list of products from wishlist
    Set<ProductDto> getProductsFromWishList(Long userId) throws Exception;
}
