package com.example.Mini.Online.Market.userpoints.repository;

import com.example.Mini.Online.Market.mockfactory.User;
import com.example.Mini.Online.Market.userpoints.domain.UserPoint;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserPointRepository extends CrudRepository<UserPoint, Long> {
    Optional<UserPoint> findUserPointByUser(User user);
}
