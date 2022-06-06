package com.spacesupply.product.service;

import com.spacesupply.product.model.Product;
import com.spacesupply.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repository;

    @Override
    public List<Product> getAll() {
        return repository.findAll();
    }

    @Override
    public Product getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public List<Product> getByName(String name) {
        return repository.findByNameIgnoreCase(name);
    }

    @Override
    public long getCount() {
        return repository.count();
    }

    @Override
    public ResponseEntity<Object> createProduct(Product product) {
        Product newProduct;
        try{
            newProduct = repository.save(product);
        }
        catch(Exception ex) {
            String message = ex.getCause().getMessage();
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Product> updateProduct(Long id, Product product){
        Product currentProduct = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        currentProduct.setName(product.getName());
        currentProduct.setPrice(product.getPrice());
        currentProduct.setQuantity(product.getQuantity());

        final Product updatedProduct = repository.save(currentProduct);

        return ResponseEntity.ok(updatedProduct);

    }

    @Override
    public String deleteProduct(Long id) {
        Product currentProduct = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        String name = currentProduct.getName();

        repository.delete(currentProduct);

        return String.format("Product with id %d and name %s is no longer available",
                id, name);
    }

}
