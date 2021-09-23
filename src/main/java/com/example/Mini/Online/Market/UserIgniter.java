package com.example.Mini.Online.Market;


import com.example.Mini.Online.Market.domain.Role;
import com.example.Mini.Online.Market.domain.User;
import com.example.Mini.Online.Market.domain.UserStatus;
import com.example.Mini.Online.Market.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@RequiredArgsConstructor
@Component
public class UserIgniter implements CommandLineRunner {
    private final UserService userService;

    @Override
    public void run(String... args) throws Exception {
//        Role adminRole = new Role();
//        adminRole.setName("ADMIN");
        User admin = new User();
        admin.setUsername("admin");
        admin.setName("admin");
        admin.setPassword("pass");
//        admin.setRole(adminRole);
        admin.setRole(Role.ADMIN);
        admin.setStatus(UserStatus.APPROVED);
        admin.setEmail("admin@adim.com");
        userService.save(admin);
// //       roleService.save(adminRole);
//        Role buyer = new Role();
//        buyer.setName("BUYER");
//        roleService.save(buyer);
//        Role seller = new Role();
//        seller.setName("SELLER");
//        roleService.save(seller);

    }
}
