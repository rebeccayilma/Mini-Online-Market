package com.example.Mini.Online.Market.controller;

import com.example.Mini.Online.Market.domain.Product;
import com.example.Mini.Online.Market.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = { "http://localhost:3000"})
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping()
    public List<Product> getProducts(){
        List<Product> products =  productService.findAll();
        return products;
    }

    @GetMapping("/{id}")
    public Optional<Product> getPostById(@PathVariable long id){
        return productService.findById(id);
    }

    @PostMapping
    public void addProduct(@RequestBody Product product){
        productService.addProduct(product);
    }

    @PutMapping
    public Product updateProduct(@RequestBody Product product) {
        return productService.update(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable long id) {
        productService.deleteById(id);
    }

//    @GetMapping("/{id}/reviews")
//    public List<Review> getReviewsOfProduct(@PathVariable long id){
//        return productService.getProductReview(id);
//    }
}
