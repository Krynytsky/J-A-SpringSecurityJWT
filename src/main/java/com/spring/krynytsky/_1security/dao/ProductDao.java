package com.spring.krynytsky._1security.dao;

import com.spring.krynytsky._1security.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDao extends JpaRepository <Product,Integer> {
}
