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
import project.webshop.service.WishListService;
import project.webshop.utils.Constants;
import project.webshop.utils.Converter;

import java.util.HashSet;
import java.util.Set;

@Component
@Transactional
public class WishListServiceImpl implements WishListService {

    @Autowired
    @Qualifier("userRepository")
    private UserRepository userRepository;

    @Autowired
    @Qualifier("productRepository")
    private ProductRepository productRepository;

    @Override
    public ProductDto addProductToWishList(Long userId, Long productId) throws Exception {
        User user = userRepository.findOne(userId);
        if (user == null) {
            throw new Exception(Constants.MESSAGE_NOT_FOUND_USER);
        }
        Product product = productRepository.findOne(productId);
        if (product == null) {
            throw new Exception(Constants.MESSAGE_NOT_FOUND_PRODUCT);
        }
        Set<Product> products = user.getWishProducts();
        if (products == null) {
            products = new HashSet<>();
        }
        products.add(product);
        user.setWishProducts(products);
        userRepository.save(user);
        return Converter.toProductWithoutSpecificationsDto(product);
    }

    @Override
    public void deleteProductFromWishList(Long userId, Long productId) throws Exception {
        User user = userRepository.findOne(userId);
        if (user == null) {
            throw new Exception(Constants.MESSAGE_NOT_FOUND_USER);
        }
        Set<Product> products = user.getWishProducts();
        if (products == null) {
            throw new Exception("User has not wish products!");
        }
        Product searchedProduct = null;
        for (Product productEntity : products) {
            if (productEntity.getId().equals(productId)) {
                searchedProduct = productEntity;
                break;
            }
        }
        if (searchedProduct == null) {
            throw new Exception("User has not this wish product!");
        }
        products.remove(searchedProduct);
        user.setWishProducts(products);
        userRepository.save(user);
    }

    @Override
    public Set<ProductDto> getProductsFromWishList(Long userId) throws Exception {
        User user = userRepository.findOne(userId);
        if (user == null) {
            throw new Exception(Constants.MESSAGE_NOT_FOUND_USER);
        }
        if (user.getWishProducts() == null) {
            return null;
        }
        Set<ProductDto> productDtos = new HashSet<>();
        Set<Product> products = user.getWishProducts();
        for (Product product : products) {
            productDtos.add(Converter.toProductWithoutSpecificationsDto(product));
        }
        return productDtos;
    }
}
