package com.example.Mini.Online.Market.service;

import com.example.Mini.Online.Market.domain.Address;

import java.util.Optional;

public interface AddressService {
    public Optional<Address> getOne(long id);
}
