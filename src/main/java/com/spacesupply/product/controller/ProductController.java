package com.spacesupply.product.controller;

import com.spacesupply.product.model.Product;
import com.spacesupply.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("products")
public class ProductController {
    private final Environment env;
    private final ProductService productService;

    @GetMapping("/")
    public String getHome() {
        return  "This is the Product Microservice";
    }

    @GetMapping("/status")
    public String getStatus(){
        return "You successfully logged in on port: " + env.getProperty("server.port");
    }

    @GetMapping("/all")
    public List<Product> getAll() {
        return productService.getAll();
    }

    @GetMapping("/id/{id}")
    public Product getById(@PathVariable("id") Long productId) {
        return productService.getById(productId);
    }

    @GetMapping("/name/{name}")
    public List<Product> getByName(@PathVariable("name") String name) {
        return productService.getByName(name);
    }

//    @PostMapping("/add/{product}")
//    public ResponseBody postProduct(@PathVariable("product") String name) {
//        return ResponseBody();
//    }

}
