package com.example.Mini.Online.Market.service;

import com.example.Mini.Online.Market.domain.Address;
import com.example.Mini.Online.Market.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    AddressRepository addressRepository;

    @Override
    public Optional<Address> getOne(long id) {
        return addressRepository.findById(id);
    }
}
