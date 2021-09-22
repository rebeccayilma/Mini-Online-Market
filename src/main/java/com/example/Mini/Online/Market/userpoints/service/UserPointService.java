package com.example.Mini.Online.Market.userpoints.service;

import com.example.Mini.Online.Market.mockfactory.User;
import com.example.Mini.Online.Market.userpoints.domain.UserPoint;

import java.util.List;

public interface UserPointService {
    UserPoint save(UserPoint userPoint);

    void incrementPoints(User user, int points);

    void decrementPoints(User user, int points);

    List<UserPoint> getAll();

    UserPoint getUserPoints(long userId);
}
