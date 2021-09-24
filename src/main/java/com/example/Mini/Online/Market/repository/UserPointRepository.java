package com.example.Mini.Online.Market.repository;

import com.example.Mini.Online.Market.domain.User;
import com.example.Mini.Online.Market.domain.UserPoint;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserPointRepository extends CrudRepository<UserPoint, Long> {
    Optional<UserPoint> findUserPointByUser(User user);
    List<UserPoint> findAll();
}
