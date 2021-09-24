package com.example.Mini.Online.Market.domain;

import com.example.Mini.Online.Market.orders.domain.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
//    @OneToOne(cascade = CascadeType.ALL)
    private Role role;
    @NotEmpty
    private UserStatus status;
    @OneToMany(mappedBy ="user",cascade = CascadeType.ALL)
    List<Order> orders;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    List<Address> addresses;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                ", status=" + status +
                ", orders=" + orders +
                ", addresses=" + addresses +
                '}';
    }
}
