package com.spacesupply.product.controller;

import com.spacesupply.product.model.Product;
import com.spacesupply.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//import javax.validation.Valid;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("products")
public class ProductController {
    private final Environment env;
    private final ProductService productService;

    @GetMapping("/")
    public String getHome() {
        String port = env.getProperty("server.port");
        String title = "<h1>This is the Product Microservice</h1>";
        String text = "<p>You have access to the following mappings using <i>localhost:"+port+"/products</i></p>";
        String[] mappings = new String[]{"status", "all", "id/{id}", "name/{name}",
                "count", "create", "update/{id}", "delete/{id}"};
        String mappingItems = Arrays.stream(mappings)
                .map(mapping -> "<li>/"+mapping+"</li>")
                .collect(Collectors.joining());

        StringBuilder sb = new StringBuilder();
        sb.append(title).append(text);
        sb.append("<ul>").append(mappingItems).append("</ul>");
        return  sb.toString();
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

    @GetMapping("/count")
    public long getCount(){
        return productService.getCount();
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product){
        return productService.updateProduct(id, product);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        return productService.deleteProduct(id);
    }

}
