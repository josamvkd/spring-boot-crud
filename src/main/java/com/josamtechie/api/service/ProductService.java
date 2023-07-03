package com.josamtechie.api.service;

import com.josamtechie.api.model.Product;
import com.josamtechie.api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public Product add(Product product) {
        return repository.save(product);

    }

    public List<Product> getProducts() {
        return repository.findAll();
    }

    public Product getProductById(int id) {
        return repository.findById(id).get();
    }

    public String deleteProductById(int id) {
        repository.deleteById(id);
        return "Product "+id+" Deleted successfully..";
    }

    public Product updateProduct(int id, Product productRequest) {
        Product existingProduct = repository.findById(id).get();
        existingProduct.setName(productRequest.getName());
        existingProduct.setPrice(productRequest.getPrice());
        existingProduct.setDescription(productRequest.getDescription());
        existingProduct.setProductType(productRequest.getProductType());
        return repository.save(existingProduct);
    }

    public Product updateProductFields(int id, Map<String, Object> fields) {
        Optional<Product> existingProduct = repository.findById(id);
        if(existingProduct.isPresent()){
            fields.forEach((key,value) ->{
                Field field = ReflectionUtils.findField(Product.class, key);
                field.setAccessible(true);
                ReflectionUtils.setField(field,existingProduct.get(),value);
            });
            return repository.save(existingProduct.get());
        }
        return null;
    }
}
