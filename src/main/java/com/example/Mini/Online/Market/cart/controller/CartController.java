package com.example.Mini.Online.Market.cart.controller;

import com.example.Mini.Online.Market.cart.domain.CheckoutAddressDTO;
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

    @GetMapping()
    public ResponseEntity<?> getCart() {
        User user = mockUser();
        Optional<ShoppingCart> cart = shoppingCartService.getCart(user);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<?> addToCart(@RequestBody ProductDTO productDto) {
        User user = mockUser();
        ShoppingCart shoppingCart = shoppingCartService.addToCart(productDto.getId(), productDto.getQuantity(), user);
        return new ResponseEntity<>(shoppingCart, HttpStatus.OK);
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

    @PostMapping(value = "/remove/{productId}")
    public ResponseEntity<?> removeFromCart(@PathVariable Long productId) {
        //TODO: Mock user => Should come from the session
        User user = mockUser();
        ShoppingCart shoppingCart = shoppingCartService.removeFromCart(productId, user);
        return new ResponseEntity<>(shoppingCart, HttpStatus.OK);
    }

    @PostMapping(value = "/checkout/address")
    public ResponseEntity<?> addAddressToCart(@RequestBody CheckoutAddressDTO addressDTO) {
        //TODO: Mock user => Should come from the session
        User user = mockUser();
        ShoppingCart shoppingCart = shoppingCartService.addAddressToCart(user,
                addressDTO.getBillingId(), addressDTO.getBillingId());
        return new ResponseEntity<>(shoppingCart, HttpStatus.OK);
    }

    public User mockUser() {
        return new User(1L, "Johnstone", "Ananda", "johnolwamba@gmail.com");
    }
}
