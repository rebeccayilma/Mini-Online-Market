package com.example.Mini.Online.Market.service;

import com.example.Mini.Online.Market.domain.User;
import com.example.Mini.Online.Market.domain.UserStatus;
import com.example.Mini.Online.Market.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;



    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getById(long id) {
        return userRepository.findById(id);
    }

    @Override
    public void save(User user) {
        Optional<User> checkUsername = userRepository.findByUsername(user.getUsername());
        if (checkUsername.isEmpty()){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setStatus(UserStatus.PENDING);
            userRepository.save(user);
        }
    }

    @Override
    public List<User> getByStatus() {
        return userRepository.findPendingSellers();
    }

    @Override
    public List<User> getSellers() {
        return userRepository.findAllSellers();
    }

    @Override
    public Optional<User> findByUsername(String s) {
        return userRepository.findByUsername(s);
    }

    @Override
    public Optional<User> getAuthenticatedUser(Authentication authentication) {
        MyUserDetails myUserDetails = (MyUserDetails) authentication.getPrincipal();
        String username = myUserDetails.getUsername();
        return findByUsername(username);
    }
}
