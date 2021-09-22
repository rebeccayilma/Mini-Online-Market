package com.example.Mini.Online.Market.cart.controller;

import com.example.Mini.Online.Market.cart.domain.CheckoutAddressDTO;
import com.example.Mini.Online.Market.cart.domain.ProductDTO;
import com.example.Mini.Online.Market.cart.domain.ShoppingCart;
import com.example.Mini.Online.Market.cart.service.ShoppingCartService;
import com.example.Mini.Online.Market.domain.User;
import com.example.Mini.Online.Market.orders.domain.Order;
import com.example.Mini.Online.Market.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    ShoppingCartService shoppingCartService;

    @Autowired
    UserService userService;

    @GetMapping()
    public ResponseEntity<?> getCart(Authentication authentication) {
        Optional<User> userOptional = userService.getAuthenticatedUser(authentication);
        if (userOptional.isPresent()) {
            Optional<ShoppingCart> cart = shoppingCartService.getCart(userOptional.get());
            return new ResponseEntity<>(cart, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not authorized", HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping(value = "")
    public ResponseEntity<?> addToCart(@RequestBody ProductDTO productDto, Authentication authentication) {
        Optional<User> userOptional = userService.getAuthenticatedUser(authentication);
        if (userOptional.isPresent()) {
            ShoppingCart shoppingCart = shoppingCartService.addToCart(productDto.getId(), productDto.getQuantity(), userOptional.get());
            return new ResponseEntity<>(shoppingCart, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not authorized", HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/process/{cartId}")
    public ResponseEntity<?> processPayment(@PathVariable Long cartId, Authentication authentication) {
        Optional<User> userOptional = userService.getAuthenticatedUser(authentication);
        if (userOptional.isPresent()) {
            Order order = shoppingCartService.checkoutCart(cartId, userOptional.get());
            if (order == null) {
                //please add items to cart before checking out
                return new ResponseEntity<>("Order could not be completed. Please try again", HttpStatus.NO_CONTENT);
            } else {
                //TODO: Handle checkout
                return new ResponseEntity<>(order, HttpStatus.OK);
            }
        } else {
            return new ResponseEntity<>("User not authorized", HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping(value = "/remove/{productId}")
    public ResponseEntity<?> removeFromCart(@PathVariable Long productId, Authentication authentication) {
        Optional<User> userOptional = userService.getAuthenticatedUser(authentication);
        if (userOptional.isPresent()) {
            ShoppingCart shoppingCart = shoppingCartService.removeFromCart(productId, userOptional.get());
            return new ResponseEntity<>(shoppingCart, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not authorized", HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping(value = "/checkout/address")
    public ResponseEntity<?> addAddressToCart(@RequestBody CheckoutAddressDTO addressDTO,
                                              Authentication authentication) {
        Optional<User> userOptional = userService.getAuthenticatedUser(authentication);
        if (userOptional.isPresent()) {
            ShoppingCart shoppingCart = shoppingCartService.addAddressToCart(userOptional.get(),
                    addressDTO.getBillingId(), addressDTO.getBillingId());
            return new ResponseEntity<>(shoppingCart, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not authorized", HttpStatus.UNAUTHORIZED);
        }
    }
}
