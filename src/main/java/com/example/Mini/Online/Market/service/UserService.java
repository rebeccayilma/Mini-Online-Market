package com.example.Mini.Online.Market.service;


import com.example.Mini.Online.Market.domain.User;
import com.example.Mini.Online.Market.domain.UserStatus;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAll();
    Optional<User> getById(long id);
    void save(User user);
    List<User> getByStatus();
    List<User> getSellers();
    Optional<User> findByUsername(String s);
    Optional<User> getAuthenticatedUser(Authentication authentication);
}
