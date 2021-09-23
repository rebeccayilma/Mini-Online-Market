package com.example.Mini.Online.Market.service;

import com.example.Mini.Online.Market.domain.User;
import com.example.Mini.Online.Market.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

@EnableWebSecurity
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;



    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(s);
        user.orElseThrow(()->new UsernameNotFoundException("User Not Found"));
        return new MyUserDetails(user.get());
    }

}
