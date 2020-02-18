package com.spring.krynytsky._1security.services.product;

import com.spring.krynytsky._1security.dao.ProductDao;
import com.spring.krynytsky._1security.models.Product;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
@Primary
@Service("productServiceImpl1")
@AllArgsConstructor
public class ProductServiceImplementation1 implements ProductService {

    private ProductDao productDao;

    @Override
    public List<Product> save(Product product) {
        productDao.save(product);
        return productDao.findAll();
    }

    @Override
    public List<Product> findAll() {
        return productDao.findAll();
    }

    @Override
    public Product findOneById(int id) {
        return productDao.findById(id).get();
    }
}
