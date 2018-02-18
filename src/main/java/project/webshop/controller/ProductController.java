package project.webshop.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.webshop.model.dto.ProductDto;
import project.webshop.model.dto.ReviewDto;
import project.webshop.service.ProductService;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@Api(value="Products API", description="Products API")
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
    @ApiOperation(notes = "Get one product by productId and userId", value = "Find product", nickname = "getProduct",
            tags = {"products"} )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 400, message = "Invalid ID supplied", response = ApiResponse.class),
            @ApiResponse(code = 404, message = "Not found", response = ApiResponse.class)
    })
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
    @ApiOperation(value = "Get product reviews", response = Iterable.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved list of reviews"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
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
    public ResponseEntity<?> addProduct(@Valid @RequestBody ProductDto productDto,
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
