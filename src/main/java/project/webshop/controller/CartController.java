package project.webshop.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.webshop.model.dto.ProductDto;
import project.webshop.service.CartService;

import java.util.Set;

@RestController
@RequestMapping("${route.users}")
@CrossOrigin(origins = "*")
@Api(value = "Cart API")
public class CartController {

    @Autowired
    private CartService cartService;

    @RequestMapping(value = "{userId}/cart/add/{productId}", method = RequestMethod.POST,produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> addProductToCart(@PathVariable("userId") Long userId,
                                              @PathVariable("productId") Long productId,
                                              @RequestHeader(name = "Authorization") String token) throws Exception {
        ProductDto existingProductDto;
        try {
            existingProductDto = cartService.addProductToCart(userId, productId);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(existingProductDto, HttpStatus.OK);
    }

    @RequestMapping(value = "{userId}/cart/delete/{productId}", method = RequestMethod.PUT)
    public ResponseEntity<?> deleteProductFromCart(@PathVariable("userId") Long userId,
                                                   @PathVariable("productId") Long productId,
                                                   @RequestHeader(name = "Authorization") String token) throws Exception {
        try {
            cartService.deleteProductFromCart(userId, productId);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok().build();
    }

    
    @ApiOperation(value = "Get cart by user id ", response = ProductDto.class )
    @RequestMapping(value = "{userId}/cart", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getProductsFromCart(@PathVariable("userId") Long userId) throws Exception {
        Set<ProductDto> productDtos;
        try {
            productDtos = cartService.getProductsFromCart(userId);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(productDtos, HttpStatus.OK);
    }
}
