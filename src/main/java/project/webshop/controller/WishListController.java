package project.webshop.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.webshop.model.dto.ProductDto;
import project.webshop.service.WishListService;

import java.util.Set;

@RestController
@RequestMapping("${route.users}")
public class WishListController {

    @Autowired
    private WishListService wishListService;

    @RequestMapping(value = "{userId}/wishlist/add/{productId}", method = RequestMethod.PUT)
    public ResponseEntity<?> addProductToWishList(@PathVariable("userId") Long userId,
                                                  @PathVariable("productId") Long productId,
                                                  @RequestHeader(name = "Authorization") String token) throws Exception {
        ProductDto existingProductDto;
        try {
            existingProductDto = wishListService.addProductToWishList(userId, productId);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(existingProductDto, HttpStatus.OK);
    }

    // delete product from wishlist
    // PUT /userId/wishlist/delete/{productId}
    @RequestMapping(value = "{userId}/wishlist/delete/{productId}", method = RequestMethod.PUT)
    public ResponseEntity<?> deleteProductFromWishList(@PathVariable("userId") Long userId,
                                                       @PathVariable("productId") Long productId,
                                                       @RequestHeader(name = "Authorization") String token) throws Exception {
        try {
            wishListService.deleteProductFromWishList(userId, productId);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok().build();
    }

    // GET wish list of user by user id : /userId/wishlist
    @RequestMapping(value = "{userId}/wishlist", method = RequestMethod.GET)
    public ResponseEntity<?> getProductsFromWishList(@PathVariable("userId") Long userId,
                                                     @RequestHeader(name = "Authorization") String token) throws Exception {
        Set<ProductDto> productDtos;
        try {
            productDtos = wishListService.getProductsFromWishList(userId);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(productDtos, HttpStatus.OK);
    }

}
