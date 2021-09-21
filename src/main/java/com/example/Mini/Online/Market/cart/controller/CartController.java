package com.example.Mini.Online.Market.cart.controller;

import com.example.Mini.Online.Market.cart.domain.ProductDTO;
import com.example.Mini.Online.Market.cart.domain.ShoppingCart;
import com.example.Mini.Online.Market.cart.service.ShoppingCartService;
import com.example.Mini.Online.Market.mockfactory.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    ShoppingCartService shoppingCartService;

    @GetMapping("/{cartId}")
    public ResponseEntity<?> getCart(@PathVariable Long cartId) {
        Optional<ShoppingCart> cart = shoppingCartService.getCart(cartId);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<?> addToCart(@RequestBody ProductDTO productDto) {
//        ResponseEntity responseEntity = productFeignClient.isEnoughInStock(productDto.getProductNumber(), quantity);
        User user = mockUser();
        shoppingCartService.addToCart(productDto.getId(), productDto.getQuantity(), user);
//        return new ResponseEntity<>("Added to cart Successfully", HttpStatus.OK);
        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }

    @PostMapping("/checkout/{cartId}")
    public ResponseEntity<?> checkoutCart(@PathVariable Long cartId) {
        User user = mockUser();
        Optional<ShoppingCart> shoppingCart = shoppingCartService.checkoutCart(cartId, user);
        if (shoppingCart.isEmpty() || shoppingCart.get().getCartLine().isEmpty()) {
            //please add items to cart before checking out
            return new ResponseEntity<>("Please add items to cart before checkout", HttpStatus.NO_CONTENT);
        } else {
            //TODO: Handle checkout
            return new ResponseEntity<>("Checkout successful", HttpStatus.OK);
        }
    }

    @PostMapping(value = "/remove/{cartId}/{productId}")
    public ResponseEntity<?> removeFromCart(@PathVariable Long cartId, @PathVariable Long productId) {
        //TODO: Mock user => Should come from the session
        User user = mockUser();
        shoppingCartService.removeFromCart(cartId, productId, user);
        return new ResponseEntity<>("Removed from cart Successfully", HttpStatus.OK);
    }

    public User mockUser() {
        return new User(1L, "Johnstone", "Ananda", "johnolwamba@gmail.com");
    }
}
