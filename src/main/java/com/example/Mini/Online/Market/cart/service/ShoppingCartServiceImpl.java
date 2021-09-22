package com.example.Mini.Online.Market.cart.service;

import com.example.Mini.Online.Market.cart.domain.CartLine;
import com.example.Mini.Online.Market.cart.domain.ShoppingCart;
import com.example.Mini.Online.Market.cart.repository.ShoppingCartRepository;
import com.example.Mini.Online.Market.domain.Address;
import com.example.Mini.Online.Market.domain.Product;
import com.example.Mini.Online.Market.domain.User;
import com.example.Mini.Online.Market.email.EmailService;
import com.example.Mini.Online.Market.service.AddressService;
import com.example.Mini.Online.Market.orders.domain.Order;
import com.example.Mini.Online.Market.orders.domain.OrderAdapter;
import com.example.Mini.Online.Market.orders.service.OrderService;
import com.example.Mini.Online.Market.service.ProductService;
import com.sparkpost.exception.SparkPostException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    ShoppingCartRepository shoppingCartRepository;

    @Autowired
    ProductService productService;

    @Autowired
    AddressService addressService;

    @Autowired
    OrderService orderService;

    @Autowired
    EmailService emailService;

    @Override
    public ShoppingCart addToCart(Long productId, int quantity, User user) {
        Optional<ShoppingCart> optionalCart = shoppingCartRepository.findShoppingCartByUser(user);
        Optional<Product> product = productService.findById(productId);
        ShoppingCart shoppingCart = optionalCart.orElseGet(() -> createCart(user));
        if (!isStockValid(shoppingCart, product, quantity)) {
            throw new NoSuchElementException("Not enough quantity in stock. Try again");
        } else {
            if (product.isPresent()) {
                shoppingCart.addToCart(product.get(), quantity);
                return shoppingCartRepository.save(shoppingCart);
            } else {
                throw new NoSuchElementException("Product not found. Try again");
            }
        }
    }

    private boolean isStockValid(ShoppingCart cart, Optional<Product> product, int quantity) {
        if (product.isPresent()) {
            CartLine cartLine = null;
            if (cart.getCartLine() != null) {
                for (CartLine cLine : cart.getCartLine()) {
                    if (Objects.equals(cLine.getProduct().getId(), product.get().getId())) {
                        cartLine = cLine;
                        break;
                    }
                }
            }

            if (cartLine == null) return false;
            return productService.isEnoughInStock(product.get().getId(), cartLine.getQuantity() + quantity);
        } else {
            return false;
        }
    }

    @Override
    public ShoppingCart removeFromCart(Long productId, User user) {
        Optional<ShoppingCart> userCart = shoppingCartRepository.findShoppingCartByUser(user);
        if (userCart.isPresent()) {
            Optional<Product> product = productService.findById(productId);
            if (product.isPresent()) {
                ShoppingCart cart = userCart.get();
                cart.removeFromCart(product.get());
                return shoppingCartRepository.save(cart);
            } else {
                throw new NoSuchElementException("Product not found. Try again");
            }
        } else {
            throw new NoSuchElementException(user.getName() + " does not have an existing cart. Try again");
        }
    }

    public ShoppingCart createCart(User user) {
        ShoppingCart shoppingCart = new ShoppingCart();
        ShoppingCart shoppingCart1 = shoppingCartRepository.save(shoppingCart);
        shoppingCart1.setUser(user);
        return shoppingCartRepository.save(shoppingCart1);
    }

    @Override
    public Order checkoutCart(Long cartId, User user) {

        Optional<ShoppingCart> shoppingCart = shoppingCartRepository.findById(cartId);

        //TODO:
        /*
        -validate payments
        -create order through adapter
        -send out email to user
        -return order
         */


        if (shoppingCart.isPresent()) {
            Order order = OrderAdapter.parseCartToOrder(shoppingCart.get());
            orderService.save(order);
            //send email
            try {
                emailService.orderPlacementEmail(order);
            } catch (SparkPostException ex) {
                System.out.println(ex);
            }

            return order;
        } else {
            throw new NoSuchElementException("Cart ID not found. Try again");
        }
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
