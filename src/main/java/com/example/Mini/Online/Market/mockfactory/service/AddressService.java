package com.example.Mini.Online.Market.mockfactory.service;

import com.example.Mini.Online.Market.mockfactory.Address;

import java.util.Optional;

public interface AddressService {
    public Optional<Address> getOne(long id);
}
