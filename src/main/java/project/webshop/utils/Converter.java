package project.webshop.utils;


import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import project.webshop.model.dto.*;
import project.webshop.model.entity.*;
import project.webshop.model.entity.user.Role;
import project.webshop.model.entity.user.User;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Converter {

    // toUserDto
    public static UserDto toUserDto(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setPhone(user.getPhone());
        userDto.setName(user.getName());
        userDto.setBalance(user.getBalance());
        // Address
        Address address = user.getAddress();
        if(address != null){
            AddressDto addressDto = toAddressDto(address);
            userDto.setAddress(addressDto);
        }
        // Roles
        if (user.getRoles() != null) {
            Set<Role> roles = user.getRoles();
            Set<GrantedAuthority> collectionRoles = new HashSet<>();
            for (Role role : roles) {
                collectionRoles.add(new SimpleGrantedAuthority(role.getName()));
            }
            userDto.setRoles(collectionRoles);
        }
        return userDto;

    }
    // UserDto -> User
    public static User toUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setName(userDto.getName());
        user.setPhone(userDto.getPhone());
        user.setBalance(userDto.getBalance());
        // Address
        AddressDto addressDto = userDto.getAddress();
        if (addressDto != null) {
            Address address = toAddressEntity(addressDto);
            user.setAddress(address);
        }
        //Role
        if (userDto.getRoles() != null) {
            Set<GrantedAuthority> collection = userDto.getRoles().stream().collect(Collectors.toSet());
            Set<Role> roles = new HashSet<>();
            for (GrantedAuthority grantedAuthority : collection) {
                Role role = new Role();
                role.setName(grantedAuthority.getAuthority());
            }
            user.setRoles(roles);
        }
        return user;
    }

    // AddressDto -> Address
    public static Address toAddressEntity(AddressDto addressDto) {
        Address address = new Address();
        address.setStreet(addressDto.getStreet());
        address.setCity(addressDto.getCity());
        address.setZipcode(addressDto.getZipcode());
        address.setCountry(addressDto.getCountry());
        return address;
    }


    // toAddressDto
    public static AddressDto toAddressDto(Address address) {
        AddressDto addressDto = new AddressDto();
        addressDto.setStreet(address.getStreet());
        addressDto.setCity(address.getCity());
        addressDto.setZipcode(address.getZipcode());
        addressDto.setCountry(address.getCountry());
        return addressDto;
    }



    public static RoleDto toRoleDto(Role role) {
        RoleDto roleDto = new RoleDto();
        roleDto.setId(role.getId());
        roleDto.setName(role.getName());
        return roleDto;
    }

    public static Role toRole(RoleDto roleDto) {
        Role role = new Role();
        role.setId(roleDto.getId());
        role.setName(roleDto.getName().toUpperCase());
        return role;
    }

    public static ReviewDto toReviewDto(Review productReview) throws Exception {
        ReviewDto review = new ReviewDto();
        review.setMessage(productReview.getComment());
        review.setStars(productReview.getStars());
        return review;
    }

    public static Review toReview(ReviewDto reviewDto) throws Exception {
        Review review = new Review();
        review.setComment(reviewDto.getMessage());
        review.setStars(reviewDto.getStars());
        return review;
    }

    public static Product toProduct(ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setImage(productDto.getImage());
        product.setBrand(productDto.getBrand());
        product.setCode(productDto.getCode());
        product.setColor(productDto.getColor());
        product.setCountry(productDto.getCountry());
        if (productDto.getSizes() != null) {
            product.setSizes(StringUtils.join(productDto.getSizes(), ", "));
        }
        Set<ImageDto> imagesDtos = productDto.getImages();
        if (imagesDtos != null) {
            Set<Image> images = new HashSet<>();
            for (ImageDto imageDto : imagesDtos) {
                Image image = new Image();
                image.setProduct(product);
                image.setImage(imageDto.getImage());
                images.add(image);
            }
            product.setProductImages(images);
        }
        Set<ReviewDto> reviewDtos = productDto.getReviews();
        if (reviewDtos != null) {
            Set<Review> reviews = new HashSet<>();
            for (ReviewDto reviewDto : reviewDtos) {
                Review review = new Review();
                review.setProduct(product);
                review.setComment(reviewDto.getMessage());
                review.setStars(reviewDto.getStars());
                reviews.add(review);
            }
            product.setProductReviews(reviews);
        }
        CategoryDto categoryDto = productDto.getCategory();
        if (categoryDto != null) {
            Category category = toCategory(categoryDto);
            product.setCategory(category);
        }
        return product;
    }

    public static ProductDto toProductWithoutSpecificationsDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setImage(product.getImage());
        Set<Image> images = product.getProductImages();
        if (images != null) {
            Set<ImageDto> imageDtos = new HashSet<>();
            for (Image image : images) {
                ImageDto imageDto = new ImageDto();
                imageDto.setImage(image.getImage());
                imageDtos.add(imageDto);
            }
            productDto.setImages(imageDtos);
        }
        Set<Review> reviews = product.getProductReviews();
        if (reviews != null) {
            Set<ReviewDto> reviewDtos = new HashSet<>();
            for (Review review : reviews) {
                ReviewDto reviewDto = new ReviewDto();
                reviewDto.setMessage(review.getComment());
                reviewDto.setStars(review.getStars());
                reviewDtos.add(reviewDto);
            }
            productDto.setReviews(reviewDtos);
        }
        return productDto;
    }

    public static ProductDto toProductSpecificationsDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setCountry(product.getCountry());
        productDto.setColor(product.getColor());
        productDto.setCode(product.getCode());
        productDto.setBrand(product.getBrand());
        if (product.getSizes() != null) {
            productDto.setSizes(new HashSet<>(Arrays.asList(product.getSizes().split(", "))));
        }
        return productDto;
    }

    public static Category toCategory(CategoryDto categoryDto) {
        Category category = new Category();
        category.setId(categoryDto.getId());
        category.setName(categoryDto.getName());
        category.setImage(categoryDto.getImage());
        Set<CategoryDto> childrenDtos = categoryDto.getChildren();
        if (childrenDtos != null) {
            Set<Category> children = new HashSet<>();
            for (CategoryDto childDto : childrenDtos) {
                Category child = toCategory(childDto);
                child.setParent(category);
                children.add(child);
            }
            category.setChildren(children);
        }
        return category;
    }

    public static CategoryDto toCategoryDto(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        categoryDto.setImage(category.getImage());
        Set<Category> children = category.getChildren();
        if (children != null) {
            Set<CategoryDto> childrenDtos = new HashSet<>();
            for (Category child : children) {
                CategoryDto childDto = toCategoryDto(child);
                childDto.setParentId(child.getParent().getId());
                childrenDtos.add(childDto);
            }
            categoryDto.setChildren(childrenDtos);
        }
        return categoryDto;
    }

    public static CategoryDto toCategoryWithoutChildrenDto(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        categoryDto.setImage(category.getImage());
        return categoryDto;
    }

    public static OrderDto toOrderDto(Order orderEntity) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(orderEntity.getId());
        orderDto.setStatus(orderEntity.getStatus().name());
        orderDto.setDate(orderEntity.getDate());
        Set<Product> products = orderEntity.getOrderProducts();
        if (products != null) {
            Set<ProductDto> productDtos = new HashSet<>();
            for (Product product : products) {
                productDtos.add(toProductWithoutSpecificationsDto(product));
            }
            orderDto.setProducts(productDtos);
        }
        return orderDto;
    }

}
