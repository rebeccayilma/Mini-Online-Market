package com.example.Mini.Online.Market.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String street;
    private String city;
    private String state;
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    private User user;

    @Override
    public String toString() {
        return "Address: " +
                "\nStreet: " + street + '\'' +
                "\nCity='" + city + '\'' +
                "\nState='" + state + '\'';
    }
}
