package com.example.Mini.Online.Market.cart.domain;

import com.example.Mini.Online.Market.mockfactory.Product;
import com.example.Mini.Online.Market.mockfactory.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Iterator;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "cart_line_items", referencedColumnName = "id")
    private List<CartLine> cartLine;

    public void addToCart(Product product, int quantity) {
        if (cartLine != null) {
            for (CartLine cline : cartLine) {
                if (cline.getProduct().getId().equals(product.getId())) {
                    cline.setQuantity(cline.getQuantity() + quantity);
                    return;
                }
            }
        }

        CartLine cline = new CartLine();
        cline.setProduct(product);
        cline.setQuantity(quantity);
        cartLine.add(cline);
    }

    public double getTotalPrice() {
        double totalPrice = 0.0;
        for (CartLine cline : cartLine) {
            totalPrice = totalPrice + (cline.getProduct().getPrice() * cline.getQuantity());
        }
        return totalPrice;
    }

    public void removeFromCart(Product product) {
        Iterator<CartLine> iter = cartLine.iterator();
        while (iter.hasNext()) {
            CartLine cline = iter.next();
            if (cline.getProduct().getId().equals(product.getId())) {
                if (cline.getQuantity() > 1) {
                    cline.setQuantity(cline.getQuantity() - 1);
                } else {
                    iter.remove();
                }
            }
        }
    }
}
