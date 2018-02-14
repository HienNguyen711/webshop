package project.webshop.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.webshop.model.dto.ProductDto;
import project.webshop.model.dto.ReviewDto;
import project.webshop.service.ProductService;

import java.util.List;
import java.util.Set;

@RestController
public class ProductController {
    // product service
    @Autowired
    private ProductService productService;

    // GET ALL PRODUCTS BY CATEGORY
    @RequestMapping(value = "catalog", method = RequestMethod.GET)
    public ResponseEntity<?> getProducts(@RequestParam("offset") int offset,
                                         @RequestParam("limit") int limit,
                                         @RequestParam("category") long categoryId) throws Exception {

        List<ProductDto> productDtos;
        try {
            productDtos = productService.getProducts(offset, limit, categoryId);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(productDtos, HttpStatus.OK);
    }

    // GET PRODUCT BY USER ID AND PRODUCT ID
    @RequestMapping(value = "catalog/{productId}/user/{userId}", method = RequestMethod.GET)
    public ResponseEntity<?> getProduct(@PathVariable("productId") Long productId,
                                        @PathVariable("userId") Long userId) throws Exception {
        ProductDto productDto;
        try {
            productDto = productService.getProduct(productId, userId);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }

    // GET ALL PRODUCT REVIEWS
    @RequestMapping(value = "catalog/{productId}/reviews", method = RequestMethod.GET)
    public ResponseEntity<?> getProductReviews(@PathVariable("productId") Long productId) throws Exception {
        Set<ReviewDto> reviewDtos;
        try {
            reviewDtos = productService.getProductReviews(productId);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(reviewDtos, HttpStatus.OK);
    }

    // UPDATE PRODUCT
    @RequestMapping(value = "admin/catalog/update", method = RequestMethod.PUT)
    public ResponseEntity<?> updateProduct(@RequestBody ProductDto productDto,
                                           @RequestHeader(name = "Authorization") String token) throws Exception {
        ProductDto existingProductDto;
        try {
            existingProductDto = productService.editProduct(productDto);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(existingProductDto, HttpStatus.OK);
    }

    // ADD PRODUCT
    @RequestMapping(value = "admin/catalog/add", method = RequestMethod.POST)
    public ResponseEntity<?> addProduct(@RequestBody ProductDto productDto,
                                        @RequestHeader(name = "Authorization") String token) throws Exception {
        ProductDto existingProductDto;
        try {
            existingProductDto = productService.addProduct(productDto);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(existingProductDto, HttpStatus.OK);
    }

    // EDIT PRODUCT : EDIT CATEGORY OF PRODUCT
    // PUT /admin/catalog/{productId}/add/category/{categoryId}
    @RequestMapping(value = "admin/catalog/{productId}/add/category/{categoryId}", method = RequestMethod.PUT)
    public ResponseEntity<?> setCategoryToProduct(@PathVariable("productId") Long productId,
                                                  @PathVariable("categoryId") Long categoryId,
                                                  @RequestHeader(name = "Authorization") String token) throws Exception {
        ProductDto existingProductDto;
        try {
            // productService.addCategoryToProduct
            existingProductDto = productService.addCategoryToProduct(productId, categoryId);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(existingProductDto, HttpStatus.OK);
    }

    // delete category from product
    // PUT /admin/catalog/{productId}/delete/category
    @RequestMapping(value = "admin/catalog/{productId}/delete/category", method = RequestMethod.PUT)
    public ResponseEntity<?> deleteCategoryFromProduct(@PathVariable("productId") Long productId,
                                                       @RequestHeader(name = "Authorization") String token) throws Exception {
        try {
            // productService.deleteCategoryFromProduct by productId
            productService.deleteCategoryFromProduct(productId);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok().build();
    }

    // delete product
    // by product id
    @RequestMapping(value = "admin/catalog/{id}/delete", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteProduct(@PathVariable Long id,
                                           @RequestHeader(name = "Authorization") String token) throws Exception {
        try {
            productService.deleteProduct(id);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "catalog/{productId}/specifications", method = RequestMethod.GET)
    public ResponseEntity<?> getProductSpecifications(@PathVariable("productId") Long id) throws Exception {
        ProductDto productSpecificationsDto;
        try {
            productSpecificationsDto = productService.getProductSpecifications(id);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(productSpecificationsDto, HttpStatus.OK);
    }
}
