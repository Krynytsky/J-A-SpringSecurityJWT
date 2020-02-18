package com.spring.krynytsky._1security.services.user;

import com.spring.krynytsky._1security.dao.UserDAO;
import com.spring.krynytsky._1security.models.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImplementation implements UserService {

    private UserDAO userDAO;

    @Override
    public User findByToken(String token) {
        return userDAO.findByToken(token);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userDAO.findByUsername(s);
    }

    @Override
    public void saveUser(User user) {
    userDAO.save(user);
    }
}


