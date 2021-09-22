package com.example.Mini.Online.Market.userpoints.controller;

import com.example.Mini.Online.Market.userpoints.domain.UserPoint;
import com.example.Mini.Online.Market.userpoints.service.UserPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/points")
public class UserPointController {

    @Autowired
    UserPointService userPointService;

    @GetMapping
    public ResponseEntity<?> getAllOrders() {
        List<UserPoint> userPoints = userPointService.getAll();
        return new ResponseEntity<>(userPoints, HttpStatus.OK);
    }

}
