package com.spacesupply.product.controller;

import com.spacesupply.product.model.Product;
import com.spacesupply.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        String[] mappings = new String[]{"status", "all", "id/{id}", "name/{name}", "count"};
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

//    @PostMapping("/add/{product}")
//    public ResponseBody postProduct(@PathVariable("product") String name) {
//        return ResponseBody();
//    }

}
