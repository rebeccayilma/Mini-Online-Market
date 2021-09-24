package com.example.Mini.Online.Market.repository;

import com.example.Mini.Online.Market.domain.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends CrudRepository<Address,Long> {
    List<Address> findAll();
}
