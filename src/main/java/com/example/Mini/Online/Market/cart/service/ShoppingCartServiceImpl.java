package com.example.Mini.Online.Market.cart.service;

import com.example.Mini.Online.Market.cart.domain.CartLine;
import com.example.Mini.Online.Market.cart.domain.ProcessPaymentDTO;
import com.example.Mini.Online.Market.cart.domain.ShoppingCart;
import com.example.Mini.Online.Market.cart.repository.ShoppingCartRepository;
import com.example.Mini.Online.Market.domain.Address;
import com.example.Mini.Online.Market.domain.Product;
import com.example.Mini.Online.Market.domain.User;
import com.example.Mini.Online.Market.email.EmailService;
import com.example.Mini.Online.Market.orders.domain.Order;
import com.example.Mini.Online.Market.orders.domain.OrderAdapter;
import com.example.Mini.Online.Market.orders.service.OrderService;
import com.example.Mini.Online.Market.payment.domain.Payment;
import com.example.Mini.Online.Market.payment.service.PaymentService;
import com.example.Mini.Online.Market.service.AddressService;
import com.example.Mini.Online.Market.service.ProductService;
import com.example.Mini.Online.Market.util.exeptionhandler.EntityNotFoundException;
import com.sparkpost.exception.SparkPostException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
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

    @Autowired
    PaymentService paymentService;

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
            if (cartLine != null) {
                //do quantity check
                if (product.get().getQuantity() < (cartLine.getQuantity() + quantity)) {
                    throw new EntityNotFoundException("Product out of stock. Try again");
                }
            }
            shoppingCart.addToCart(product.get(), quantity);
            return shoppingCartRepository.save(shoppingCart);
        } else {
            throw new EntityNotFoundException("Product not found. Try again");
        }
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
    public Payment checkoutCart(ProcessPaymentDTO processPaymentDTO, User user) {
        Optional<ShoppingCart> shoppingCart = shoppingCartRepository.findShoppingCartByUser(user);
        if (shoppingCart.isPresent()) {
            processAddress(processPaymentDTO, shoppingCart);
            //create and save order
            Order order = OrderAdapter.parseCartToOrder(shoppingCart.get());
            Order savedOrder = orderService.save(order);
            shoppingCartRepository.delete(shoppingCart.get());
            //process payments
            return processPayment(processPaymentDTO, savedOrder);
        } else {
            throw new EntityNotFoundException("You do not have an existing cart");
        }

        //TODO:
        /*
        -validate payments
        -create order through adapter => done
        -send out email to user => done
        -return order => done
         */
    }

    private Payment processPayment(ProcessPaymentDTO processPaymentDTO, Order savedOrder) {
        Payment payment = new Payment();
        payment.setCreated_at(new Date(System.currentTimeMillis()));
        payment.setOrder(savedOrder);
        payment.setType(processPaymentDTO.getType());
        payment.setTransactionCode(processPaymentDTO.getTransactionCode());
        paymentService.save(payment);

        sendEmail(payment);
        return payment;
    }

    private void processAddress(ProcessPaymentDTO processPaymentDTO, Optional<ShoppingCart> shoppingCart) {
        Address address = new Address();
        address.setCity(processPaymentDTO.getAddressCity());
        address.setState(processPaymentDTO.getAddressState());
        address.setStreet(processPaymentDTO.getAddressZip());

        shoppingCart.get().setBillingAddress(address);
        shoppingCart.get().setShippingAddress(address);
        shoppingCartRepository.save(shoppingCart.get());
    }

    private void sendEmail(Payment payment) {
        try {
            emailService.orderPlacementEmail(payment);
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
