package com.example.Mini.Online.Market.orders.repository;

import com.example.Mini.Online.Market.orders.domain.OrderLine;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderLineRepository extends CrudRepository<Long, OrderLine> {
}
