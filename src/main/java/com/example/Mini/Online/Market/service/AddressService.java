package com.example.Mini.Online.Market.service;

import com.example.Mini.Online.Market.domain.Address;

import java.util.List;
import java.util.Optional;

public interface AddressService {
    Optional<Address> getOne(long id);
    Optional<Address> findById(long id) ;
    List<Address> getAll();
    void save(Address address);

}
