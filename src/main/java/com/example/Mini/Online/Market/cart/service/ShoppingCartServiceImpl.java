package com.example.Mini.Online.Market.cart.service;

import com.example.Mini.Online.Market.cart.domain.ShoppingCart;
import com.example.Mini.Online.Market.cart.repository.ShoppingCartRepository;
import com.example.Mini.Online.Market.mockfactory.Product;
import com.example.Mini.Online.Market.mockfactory.User;
import com.example.Mini.Online.Market.mockfactory.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    ShoppingCartRepository shoppingCartRepository;

    @Autowired
    ProductService productService;

    @Override
    public void addToCart(Long productId, int quantity, User user) {
        Optional<ShoppingCart> optionalCart = shoppingCartRepository.findShoppingCartByUser(user);
        Optional<Product> product = productService.getOne(productId);
        if (product.isPresent()) {
            ShoppingCart shoppingCart = optionalCart.orElseGet(() -> createCart(user));
            shoppingCart.addToCart(product.get(), quantity);
            shoppingCartRepository.save(shoppingCart);
        } else {
            throw new NoSuchElementException("Product not found. Try again");
        }
    }

    @Override
    public void removeFromCart(Long cartId, Long productId, User user) {
        Optional<Product> product = productService.getOne(productId);
        if (product.isPresent()) {
            Optional<ShoppingCart> cartOptional = shoppingCartRepository.findById(cartId);
            if (cartOptional.isPresent()) {
                ShoppingCart cart = cartOptional.get();
                cart.removeFromCart(product.get());
                shoppingCartRepository.save(cart);
            } else {
                throw new NoSuchElementException("Cart ID or Product not found. Try again");
            }
        } else {
            throw new NoSuchElementException("Product not found. Try again");
        }
    }

    public ShoppingCart createCart(User user) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(user);
        return shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public Optional<ShoppingCart> checkoutCart(Long cartId, User user) {
        return shoppingCartRepository.findById(cartId);
//        if (cartOptional.isPresent()) {
//            ShoppingCart cart = cartOptional.get();
//            ShoppingCart savedCart = shoppingCartRepository.save(cart);
//            return ShoppingCartAdapter.getShoppingCartDTOFromShoppingCart(savedCart);
//        } else {
//            throw new NoSuchElementException("Cart ID not found. Try again");
//        }
    }

    @Override
    public Optional<ShoppingCart> getCart(Long cartId) {
        return shoppingCartRepository.findById(cartId);
    }
}
