package com.spacesupply.product.service;

import com.spacesupply.product.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    List<Product> getAll();
    Product getById(Long id);
    List<Product> getByName(String name);
}
