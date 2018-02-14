package project.webshop.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.webshop.model.dto.OrderDto;
import project.webshop.model.dto.ProductListDto;
import project.webshop.service.OrderService;

import java.util.Set;

@RestController
@RequestMapping("${route.users}")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "{userId}/make/order", method = RequestMethod.POST)
    public ResponseEntity<?> makeOrder(@RequestBody Set<ProductListDto> productDtos,
                                       @PathVariable("userId") Long userId,
                                       @RequestHeader(name = "Authorization") String token) throws Exception {
        OrderDto orderDto;
        try {
            orderDto = orderService.makeOrder(productDtos, userId);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(orderDto, HttpStatus.OK);
    }

    @RequestMapping(value = "{userId}/orders", method = RequestMethod.GET)
    public ResponseEntity<?> getOrders(@PathVariable("userId") Long userId,
                                       @RequestHeader(name = "Authorization") String token) throws Exception {
        Set<OrderDto> orderDtos;
        try {
            orderDtos = orderService.getOrders(userId);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(orderDtos, HttpStatus.OK);
    }

    @RequestMapping(value = "{userId}/pay/order/{orderId}", method = RequestMethod.PUT)
    public ResponseEntity<?> payOrder(@PathVariable("userId") Long userId,
                                      @PathVariable("orderId") Long orderId,
                                      @RequestHeader(name = "Authorization") String token) throws Exception {
        try {
            orderService.payOrder(userId, orderId);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "{userId}/cancel/order/{orderId}", method = RequestMethod.PUT)
    public ResponseEntity<?> cancelOrder(@PathVariable("userId") Long userId,
                                         @PathVariable("orderId") Long orderId,
                                         @RequestHeader(name = "Authorization") String token) throws Exception {
        try {
            orderService.cancelOrder(userId, orderId);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "{userId}/orders/{orderId}", method = RequestMethod.GET)
    public ResponseEntity<?> getOrder(@PathVariable("userId") Long userId,
                                      @PathVariable("orderId") Long orderId,
                                      @RequestHeader(name = "Authorization") String token) throws Exception {
        OrderDto orderDto;
        try {
            orderDto = orderService.getOrder(userId, orderId);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(orderDto, HttpStatus.OK);
    }

}

