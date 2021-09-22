package com.example.Mini.Online.Market.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

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
    private String username;
    private String password;
    @Email
    private String email;
    @OneToOne
    private Role role;
    private UserStatus status;
//    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
//    List<Order> orders;
//    List<Adddress> adddresses;
}
