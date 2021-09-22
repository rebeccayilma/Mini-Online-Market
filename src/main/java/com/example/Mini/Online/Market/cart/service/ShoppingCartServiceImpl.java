package com.example.Mini.Online.Market.cart.service;

import com.example.Mini.Online.Market.cart.domain.CartLine;
import com.example.Mini.Online.Market.cart.domain.ShoppingCart;
import com.example.Mini.Online.Market.cart.repository.ShoppingCartRepository;
import com.example.Mini.Online.Market.domain.Address;
import com.example.Mini.Online.Market.domain.Product;
import com.example.Mini.Online.Market.domain.User;
import com.example.Mini.Online.Market.email.EmailService;
import com.example.Mini.Online.Market.orders.domain.Order;
import com.example.Mini.Online.Market.orders.domain.OrderAdapter;
import com.example.Mini.Online.Market.orders.service.OrderService;
import com.example.Mini.Online.Market.service.AddressService;
import com.example.Mini.Online.Market.service.ProductService;
import com.example.Mini.Online.Market.util.exeptionhandler.EntityNotFoundException;
import com.sparkpost.exception.SparkPostException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        return processAddToCart(quantity, product, shoppingCart);
    }

    private ShoppingCart processAddToCart(int quantity, Optional<Product> product, ShoppingCart shoppingCart) {
        if (product.isPresent()) {
            CartLine cartLine = null;
            if (shoppingCart.getCartLine() != null) {
                for (CartLine cLine : shoppingCart.getCartLine()) {
                    if (Objects.equals(cLine.getProduct().getId(), product.get().getId())) {
                        cartLine = cLine;
                        break;
                    }
                }
            }

            if (cartLine == null) {
                return initializeAddCartFromEmpty(quantity, product, shoppingCart);
            } else {
                if (product.get().getQuantity() >= (cartLine.getQuantity() + quantity)) {
                    shoppingCart.addToCart(product.get(), quantity);
                }
                return shoppingCartRepository.save(shoppingCart);
            }
        } else {
            throw new EntityNotFoundException("Product not found. Try again");
        }
    }

    private ShoppingCart initializeAddCartFromEmpty(int quantity, Optional<Product> product, ShoppingCart shoppingCart) {
        ArrayList<CartLine> cartLineList = new ArrayList<>();
        CartLine newCart = new CartLine();
        newCart.setQuantity(quantity);
        newCart.setProduct(product.get());
        cartLineList.add(newCart);
        shoppingCart.setCartLine(cartLineList);
        return shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public ShoppingCart removeFromCart(Long productId, User user) {
        Optional<ShoppingCart> userCart = shoppingCartRepository.findShoppingCartByUser(user);
        if (userCart.isPresent()) {
            Optional<Product> product = productService.findById(productId);
            if (product.isPresent()) {
                ShoppingCart cart = userCart.get();
                if (cart.getCartLine() != null) {
                    cart.removeFromCart(product.get());
                    return shoppingCartRepository.save(cart);
                } else {
                    throw new EntityNotFoundException("No cart not found. Try again");
                }
            } else {
                throw new EntityNotFoundException("Product not found. Try again");
            }
        } else {
            throw new EntityNotFoundException(user.getName() + " does not have an existing cart. Try again");
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
        -create order through adapter => done
        -send out email to user => done
        -return order => done
         */


        if (shoppingCart.isPresent()) {
            Order order = OrderAdapter.parseCartToOrder(shoppingCart.get());
            orderService.save(order);
            sendeEmail(order);
            return order;
        } else {
            throw new EntityNotFoundException("Cart ID not found. Try again");
        }
    }

    private void sendeEmail(Order order) {
        try {
            emailService.orderPlacementEmail(order);
        } catch (SparkPostException ex) {
            System.out.println(ex);
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
                throw new EntityNotFoundException("Address does not exist. Try again");
            }

        } else {
            throw new EntityNotFoundException("Cart does not exist. Try again");
        }
    }
}
