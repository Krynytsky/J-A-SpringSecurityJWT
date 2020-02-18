package com.spring.krynytsky._1security.services.product;

import com.spring.krynytsky._1security.dao.ProductDao;
import com.spring.krynytsky._1security.models.Product;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("productServiceImpl2")
@AllArgsConstructor
public class ProductServiceImplementation2 implements ProductService {

    private ProductDao productDao;

    @Override
    public List<Product> save(Product product) throws Exception {
        if(product != null ) {
            productDao.save(product);
        }else{
            throw new Exception();
        }
        return null;
    }

    @Override
    public List<Product> findAll() {
        return productDao.findAll();
    }

    @Override
    public Product findOneById(int id) {
        if (id > 0) {
            return productDao.findById(id).get();
        }
        return null;
    }

}
