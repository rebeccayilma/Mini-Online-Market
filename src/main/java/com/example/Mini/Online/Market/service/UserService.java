package com.example.Mini.Online.Market.service;


import com.example.Mini.Online.Market.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAll();
    Optional<User> getById(long id);
    void save(User user);
}
