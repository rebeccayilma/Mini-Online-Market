package com.example.Mini.Online.Market.repository;

import com.example.Mini.Online.Market.domain.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends CrudRepository<Address,Long> {
}
