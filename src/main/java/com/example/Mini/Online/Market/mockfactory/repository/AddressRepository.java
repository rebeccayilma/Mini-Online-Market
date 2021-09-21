package com.example.Mini.Online.Market.mockfactory.repository;

import com.example.Mini.Online.Market.mockfactory.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends CrudRepository<Address,Long> {
}
