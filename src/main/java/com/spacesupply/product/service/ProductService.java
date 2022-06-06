package com.spacesupply.product.service;

import com.spacesupply.product.model.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public interface ProductService {

    List<Product> getAll();
    Product getById(Long id);
    List<Product> getByName(String name);
    long getCount();
    ResponseEntity<Object> createProduct(@RequestBody Product product);
}
