package com.spring.krynytsky._1security.controllers;

import com.spring.krynytsky._1security.models.User;
import com.spring.krynytsky._1security.services.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class MainController {
    private UserService userService;
    private PasswordEncoder passwordEncoder;

    @PostMapping("/saveUser")
     public void saveUser (@RequestBody User user){
        String password = user.getPassword();
        String encodePassword = passwordEncoder.encode(password);
        user.setPassword(encodePassword);
        userService.saveUser(user);

     }
}
