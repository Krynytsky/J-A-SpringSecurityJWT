package com.spring.krynytsky._1security.services.product;

import com.spring.krynytsky._1security.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

     List<Product> save(Product product) throws Exception;

     List<Product> findAll();

     Product findOneById(int id);

}
