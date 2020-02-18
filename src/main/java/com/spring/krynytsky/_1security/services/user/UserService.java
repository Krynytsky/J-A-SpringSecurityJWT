package com.spring.krynytsky._1security.services.user;


import com.spring.krynytsky._1security.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends UserDetailsService {
    void saveUser(User user);
}
