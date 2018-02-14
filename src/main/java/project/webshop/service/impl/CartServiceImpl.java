package project.webshop.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import project.webshop.model.dto.ProductDto;
import project.webshop.model.entity.Product;
import project.webshop.model.entity.user.User;
import project.webshop.repository.ProductRepository;
import project.webshop.repository.UserRepository;
import project.webshop.service.CartService;
import project.webshop.utils.Constants;
import project.webshop.utils.Converter;

import java.util.HashSet;
import java.util.Set;

@Component
@Transactional
public class CartServiceImpl implements CartService {

    // cart service // user/ product
    @Autowired
    @Qualifier("userRepository")
    private UserRepository userRepository;
    @Autowired
    @Qualifier("productRepository")
    private ProductRepository productRepository;

    // add product to existing cart
    // userid, product id
    @Override
    public ProductDto addProductToCart(Long userId, Long productId) throws Exception {
        // get user by userid
        User user = userRepository.findOne(userId);
        if (user == null) {
            throw new Exception(Constants.MESSAGE_NOT_FOUND_USER);
        }
        Product product = productRepository.findOne(productId);
        if (product == null) {
            throw new Exception(Constants.MESSAGE_NOT_FOUND_PRODUCT);
        }
        Set<Product> products = user.getCartProducts();
        if (products == null) {
            products = new HashSet<>();
        }
        products.add(product);
        user.setCartProducts(products);
        userRepository.save(user);
        return Converter.toProductWithoutSpecificationsDto(product);
    }

    // delete product from cart
    // user id, productid
    @Override
    public void deleteProductFromCart(Long userId, Long productId) throws Exception {
        // find user by user id
        User user = userRepository.findOne(userId);
        if (user == null) {
            throw new Exception(Constants.MESSAGE_NOT_FOUND_USER);
        }
        // get cart products by user id
        Set<Product> products = user.getCartProducts();
        if (products == null) {
            throw new Exception(Constants.MESSAGE_EMPTY_CART);
        }
        Product searchedProduct = null;
        for (Product existingProduct : products) {
            // find the product that need to delete
            if (existingProduct.getId().equals(productId)) {
                searchedProduct = existingProduct;
                break;
            }
        }
        if (searchedProduct == null) {
            throw new Exception("Shopping cart has not this product!");
        }
        products.remove(searchedProduct);// remove
        user.setCartProducts(products);//save user
        userRepository.save(user);
    }

    // get products from cart by userid
    @Override
    public Set<ProductDto> getProductsFromCart(Long userId) throws Exception {
        User user = userRepository.findOne(userId);
        if (user == null) {
            throw new Exception(Constants.MESSAGE_NOT_FOUND_USER);
        }
        if (user.getCartProducts() == null) {
            return null;
        }
        Set<ProductDto> productDtos = new HashSet<>();
        Set<Product> products = user.getCartProducts();
        for (Product product : products) {
            productDtos.add(Converter.toProductWithoutSpecificationsDto(product));
        }
        return productDtos;
    }
}
