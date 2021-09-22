package com.example.Mini.Online.Market.userpoints.service;

import com.example.Mini.Online.Market.domain.User;
import com.example.Mini.Online.Market.userpoints.domain.UserPoint;
import com.example.Mini.Online.Market.userpoints.repository.UserPointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserPointServiceImpl implements UserPointService {

    @Autowired
    UserPointRepository userPointRepository;

    @Override
    public UserPoint save(UserPoint userPoint) {
        return userPointRepository.save(userPoint);
    }

    @Override
    public void incrementPoints(User user, int points) {
        Optional<UserPoint> userPoint = userPointRepository.findUserPointByUser(user);
        if (userPoint.isPresent()) {
            userPoint.get().setPoints(userPoint.get().getPoints() + points);
            userPointRepository.save(userPoint.get());
        } else {
            UserPoint userPoint1 = new UserPoint();
            userPoint1.setUser(user);
            userPoint1.setPoints(points);
            userPointRepository.save(userPoint1);
        }
    }

    @Override
    public void decrementPoints(User user, int points) {
        Optional<UserPoint> userPoint = userPointRepository.findUserPointByUser(user);
        if (userPoint.isPresent()) {
            if (userPoint.get().getPoints() < points) {
                userPoint.get().setPoints(0);
            } else {
                userPoint.get().setPoints(userPoint.get().getPoints() - points);
            }
            userPointRepository.save(userPoint.get());
        }
    }

    @Override
    public List<UserPoint> getAll() {
        return userPointRepository.findAll();
    }

    @Override
    public UserPoint getUserPoints(long userId) {
        User user = SecurityHelper.getLoggedInUser();
        Optional<UserPoint> userPointOptional = userPointRepository.findUserPointByUser(user);
        if (userPointOptional.isPresent()){
            return userPointOptional.get();
        } else {
            UserPoint userPoint = new UserPoint();
            userPoint.setUser(user);
            userPoint.setPoints(0);
            return userPointRepository.save(userPoint);
        }
    }

}
