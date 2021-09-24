package com.example.Mini.Online.Market.controller;

import com.example.Mini.Online.Market.domain.Address;
import com.example.Mini.Online.Market.service.AddressService;
import com.example.Mini.Online.Market.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/address")
public class AddressController {
    @Autowired
    UserService userService;
    @Autowired
    AddressService addressService;

    @GetMapping
    public List<Address>getAll(){
        return addressService.getAll();
    }
    @GetMapping("/{id}")
    public Optional<Address> getById(@PathVariable("id") long id){
        return addressService.findById(id);
    }
    @PostMapping("/{id}")
    public void saveAddress(@RequestBody Address address, @PathVariable long id){
        if (userService.getById(id).isPresent()) {
            address.setUser(userService.getById(id).get());
            addressService.save(address);
        }
    }
}
