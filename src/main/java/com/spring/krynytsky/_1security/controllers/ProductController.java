package com.spring.krynytsky._1security.controllers;

import com.spring.krynytsky._1security.models.Product;
import com.spring.krynytsky._1security.services.product.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@AllArgsConstructor

public class ProductController {


private ProductService productService;

    @GetMapping("/{id}")
     public Product findOne(@PathVariable int id){
        return productService.findOneById(id);
     }
     @PostMapping("/save")
      public List<Product> saveProduct(@RequestBody Product product) throws Exception {
         return productService.save(product);
      }

      @GetMapping("/products")
       public List<Product> findAll(){
          return productService.findAll();
       }

}
