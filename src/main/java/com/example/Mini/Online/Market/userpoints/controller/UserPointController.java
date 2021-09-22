package com.example.Mini.Online.Market.userpoints.controller;

import com.example.Mini.Online.Market.domain.User;
import com.example.Mini.Online.Market.service.UserService;
import com.example.Mini.Online.Market.userpoints.service.UserPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/points")
public class UserPointController {

    @Autowired
    UserPointService userPointService;

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<?> getUserPoints(Authentication authentication) {
        Optional<User> userOptional = userService.getAuthenticatedUser(authentication);
        if (userOptional.isPresent()) {
            return new ResponseEntity<>(userPointService.getUserPoints(userOptional.get()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not authorized", HttpStatus.UNAUTHORIZED);
        }
    }
}
