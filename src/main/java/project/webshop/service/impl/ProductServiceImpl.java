package project.webshop.service.impl;


import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import project.webshop.dao.ProductDao;
import project.webshop.model.dto.ProductDto;
import project.webshop.model.dto.ReviewDto;
import project.webshop.model.entity.Category;
import project.webshop.model.entity.Product;
import project.webshop.model.entity.Review;
import project.webshop.model.entity.user.User;
import project.webshop.repository.CategoryRepository;
import project.webshop.repository.ProductRepository;
import project.webshop.repository.UserRepository;
import project.webshop.service.ProductService;
import project.webshop.utils.Constants;
import project.webshop.utils.Converter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Transactional
public class ProductServiceImpl implements ProductService {
    // autowired
    @Autowired
    @Qualifier("productRepository")
    private ProductRepository productRepository;

    @Autowired
    @Qualifier("userRepository")
    private UserRepository userRepository;

    @Autowired
    @Qualifier("categoryRepository")
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductDao productDao;


    @Transactional
    @Override
    public ProductDto editProduct(@NonNull ProductDto productDto) throws Exception {
        Product product = productRepository.findOne(productDto.getId());
        if (product == null) {
            throw new Exception(Constants.MESSAGE_NOT_FOUND_PRODUCT);
        }
        product = Converter.toProduct(productDto);
        product.setId(productDto.getId());
        productRepository.save(product);
        return productDto;
    }


    @Override
    public ProductDto addProduct(@NonNull ProductDto productDto) throws Exception {
        Product product = Converter.toProduct(productDto);
        productRepository.save(product);
        productDto.setId(product.getId());
        return productDto;
    }

    @Override
    public void deleteProduct(Long id) throws Exception {
        productRepository.delete(id);
    }

    // get one product
    @Override
    public ProductDto getProduct(Long productId, Long userId) throws Exception {
        // get product
        Product product = productRepository.findOne(productId);
        if(product == null){
            return null;
        }

        ProductDto productDto = Converter.toProductWithoutSpecificationsDto(product);
        //
        if(userId != null){
            // get user by user id
            User user = userRepository.findOne(userId);
            if(user != null){
                // get wish list products from user id
                if(user.getWishProducts() != null){
                    Set<Product> products = user.getWishProducts();
                    for(Product p:products){
                        if(p.getId().equals(productId)){
                            productDto.setIsFavorite(true);
                            break;
                        }
                    }
                }
            }
        }
        return productDto;
    }

    // get products

    @Override
    public List<ProductDto> getProducts(int offset, int limit, long categoryId) throws Exception {
        List<Product> products;
        List<ProductDto> productDtos = new ArrayList<>();
        if (categoryId != 0) {
            Category category = categoryRepository.findOne(categoryId);
            if (category == null) {
                return null;
            }
            products = category.getProducts().stream().collect(Collectors.toList());
            for (Product product : products) {
                productDtos.add(Converter.toProductWithoutSpecificationsDto(product));
            }
            return productDtos;
        }
        products = productDao.getProducts(offset, limit);
        if (products == null) {
            return null;
        }
        for (Product product : products) {
            productDtos.add(Converter.toProductWithoutSpecificationsDto(product));
        }
        return productDtos;
    }

    @Override
    public Set<ReviewDto> getProductReviews(Long productId) throws Exception {
        Product product = productRepository.findOne(productId);
        if (product == null) {
            throw new Exception(Constants.MESSAGE_NOT_FOUND_PRODUCT);
        }
        Set<Review> reviews = product.getProductReviews();
        if (reviews == null) {
            return null;
        }
        Set<ReviewDto> reviewDtos = new HashSet<>();
        for (Review review : reviews) {
            ReviewDto reviewDto = Converter.toReviewDto(review);
            if (review.getUser() != null) {
                reviewDto.setAuthor(review.getUser().getName());
            }
            reviewDtos.add(reviewDto);
        }
        return reviewDtos;
    }
    @Override
    public ProductDto getProductSpecifications(Long productId) throws Exception {
        Product product = productRepository.findOne(productId);
        if (product == null) {
            throw new Exception(Constants.MESSAGE_NOT_FOUND_PRODUCT);
        }
        return Converter.toProductSpecificationsDto(product);
    }


    @Override
    public ProductDto addCategoryToProduct(Long productId, Long categoryId) throws Exception {
        Product product = productRepository.findOne(productId);
        if (product == null) {
            throw new Exception(Constants.MESSAGE_NOT_FOUND_PRODUCT);
        }
        Category category = categoryRepository.findOne(categoryId);
        if (category == null) {
            throw new Exception(Constants.MESSAGE_NOT_FOUND_CATEGORY);
        }
        product.setCategory(category);
        productRepository.save(product);
        return Converter.toProductWithoutSpecificationsDto(product);
    }

    @Override
    public void deleteCategoryFromProduct(Long productId) throws Exception {
        Product product = productRepository.findOne(productId);
        if (product == null) {
            throw new Exception(Constants.MESSAGE_NOT_FOUND_PRODUCT);
        }
        if (product.getCategory() == null) {
            throw new Exception("Product has not a category!");
        }
        product.setCategory(null);
        productRepository.save(product);
    }
}
