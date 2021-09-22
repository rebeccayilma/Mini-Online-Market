package com.example.Mini.Online.Market.cart.controller;

import com.example.Mini.Online.Market.cart.domain.CheckoutAddressDTO;
import com.example.Mini.Online.Market.cart.domain.ProductDTO;
import com.example.Mini.Online.Market.cart.domain.ShoppingCart;
import com.example.Mini.Online.Market.cart.service.ShoppingCartService;
import com.example.Mini.Online.Market.domain.User;
import com.example.Mini.Online.Market.orders.domain.Order;
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

    @GetMapping()
    public ResponseEntity<?> getCart() {
        User user = SecurityHelper.getLoggedInUser();
        Optional<ShoppingCart> cart = shoppingCartService.getCart(user);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<?> addToCart(@RequestBody ProductDTO productDto) {
        User user = SecurityHelper.getLoggedInUser();
        ShoppingCart shoppingCart = shoppingCartService.addToCart(productDto.getId(), productDto.getQuantity(), user);
        return new ResponseEntity<>(shoppingCart, HttpStatus.OK);
    }

    @PostMapping("/process/{cartId}")
    public ResponseEntity<?> processPayment(@PathVariable Long cartId) {
        User user = SecurityHelper.getLoggedInUser();
        Order order = shoppingCartService.checkoutCart(cartId, user);
        if (order == null) {
            //please add items to cart before checking out
            return new ResponseEntity<>("Order could not be completed. Please try again", HttpStatus.NO_CONTENT);
        } else {
            //TODO: Handle checkout
            return new ResponseEntity<>(order, HttpStatus.OK);
        }
    }

    @PostMapping(value = "/remove/{productId}")
    public ResponseEntity<?> removeFromCart(@PathVariable Long productId) {
        //TODO: Mock user => Should come from the session
        User user = SecurityHelper.getLoggedInUser();
        ShoppingCart shoppingCart = shoppingCartService.removeFromCart(productId, user);
        return new ResponseEntity<>(shoppingCart, HttpStatus.OK);
    }

    @PostMapping(value = "/checkout/address")
    public ResponseEntity<?> addAddressToCart(@RequestBody CheckoutAddressDTO addressDTO) {
        //TODO: Mock user => Should come from the session
        User user = SecurityHelper.getLoggedInUser();
        ShoppingCart shoppingCart = shoppingCartService.addAddressToCart(user,
                addressDTO.getBillingId(), addressDTO.getBillingId());
        return new ResponseEntity<>(shoppingCart, HttpStatus.OK);
    }
}
