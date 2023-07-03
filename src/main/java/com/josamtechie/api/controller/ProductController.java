package com.josamtechie.api.controller;

import com.josamtechie.api.model.Product;
import com.josamtechie.api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping
    public Product addProduct(@RequestBody Product product){
        return service.add(product);
    }

    @GetMapping
    public List<Product> getProducts(){
        return service.getProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable int id){
        return service.getProductById(id);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable int id, @RequestBody Product productRequest){
        return service.updateProduct(id,productRequest);
    }

    @PatchMapping("/{id}")
    public  Product updateProductFields(@PathVariable int id, @RequestBody Map<String, Object> fields){
        return service.updateProductFields(id, fields);
    }

    @DeleteMapping("{id}")
    public String deleteProductById(@PathVariable int id){
        return service.deleteProductById(id);
    }

}
