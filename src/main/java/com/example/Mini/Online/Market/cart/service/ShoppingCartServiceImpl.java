package com.example.Mini.Online.Market.cart.service;

import com.example.Mini.Online.Market.cart.domain.CheckoutAddressDTO;
import com.example.Mini.Online.Market.cart.domain.ShoppingCart;
import com.example.Mini.Online.Market.cart.repository.ShoppingCartRepository;
import com.example.Mini.Online.Market.mockfactory.Address;
import com.example.Mini.Online.Market.mockfactory.Product;
import com.example.Mini.Online.Market.mockfactory.User;
import com.example.Mini.Online.Market.mockfactory.service.AddressService;
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

    @Autowired
    AddressService addressService;

    @Override
    public ShoppingCart addToCart(Long productId, int quantity, User user) {
        Optional<ShoppingCart> optionalCart = shoppingCartRepository.findShoppingCartByUser(user);
        Optional<Product> product = productService.getOne(productId);

        if (!productService.isEnoughInStock(productId, quantity)) {
            throw new NoSuchElementException("Not enough quantity in stock. Try again");
        }

        if (product.isPresent()) {
            ShoppingCart shoppingCart = optionalCart.orElseGet(() -> createCart(user));
            shoppingCart.addToCart(product.get(), quantity);
            return shoppingCartRepository.save(shoppingCart);
        } else {
            throw new NoSuchElementException("Product not found. Try again");
        }
    }

    @Override
    public ShoppingCart removeFromCart(Long productId, User user) {
        Optional<ShoppingCart> userCart = shoppingCartRepository.findShoppingCartByUser(user);
        if (userCart.isPresent()) {
            Optional<Product> product = productService.getOne(productId);
            if (product.isPresent()) {
                ShoppingCart cart = userCart.get();
                cart.removeFromCart(product.get());
                return shoppingCartRepository.save(cart);
            } else {
                throw new NoSuchElementException("Product not found. Try again");
            }
        } else {
            throw new NoSuchElementException(user.getFirstName() + " does not have an existing cart. Try again");
        }
    }

    public ShoppingCart createCart(User user) {
        ShoppingCart shoppingCart = new ShoppingCart();
        ShoppingCart shoppingCart1 = shoppingCartRepository.save(shoppingCart);
        shoppingCart1.setUser(user);
        return shoppingCartRepository.save(shoppingCart1);
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
    public Optional<ShoppingCart> getCart(User user) {
        return shoppingCartRepository.findShoppingCartByUser(user);
    }

    @Override
    public ShoppingCart addAddressToCart(User user, long billingId, long shippingId) {
        Optional<ShoppingCart> shoppingCart = shoppingCartRepository.findShoppingCartByUser(user);
        if (shoppingCart.isPresent()) {
            Optional<Address> billingAddress = addressService.getOne(billingId);
            Optional<Address> shippingAddress = addressService.getOne(shippingId);

            if (billingAddress.isPresent() && shippingAddress.isPresent()) {
                shoppingCart.get().setBillingAddress(billingAddress.get());
                shoppingCart.get().setShippingAddress(shippingAddress.get());
                return shoppingCartRepository.save(shoppingCart.get());
            } else {
                throw new NoSuchElementException("Address does not exist. Try again");
            }

        } else {
            throw new NoSuchElementException("Cart does not exist. Try again");
        }
    }
}