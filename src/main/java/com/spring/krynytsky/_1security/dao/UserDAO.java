package com.spring.krynytsky._1security.dao;

import com.spring.krynytsky._1security.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends JpaRepository<User,Integer>  {
    User findByUsername(String username);

}
