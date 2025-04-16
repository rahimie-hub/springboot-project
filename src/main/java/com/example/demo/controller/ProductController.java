package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.util.AESUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    /**
     * Add a new product.
     * Encrypts the price before saving.
     * Example: POST /products/add?name=Widget&price=19.99
     */
    @PostMapping("/add")
    public String addProduct(@RequestParam String name,
                             @RequestParam String price,
                             HttpSession session) {
        // Ensure user is logged in.
        if (session.getAttribute("user") == null) {
            return "Please login first.";
        }
        Product product = new Product();
        product.setName(name);
        product.setEncryptedPrice(AESUtil.encrypt(price));
        productRepository.save(product);
        return "Product added successfully.";
    }

    /**
     * Update an existing product.
     * Example: POST /products/update?id=1&name=NewName&price=29.99
     */
    @PostMapping("/update")
    public String updateProduct(@RequestParam Long id,
                                @RequestParam String name,
                                @RequestParam String price,
                                HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "Please login first.";
        }
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setName(name);
            product.setEncryptedPrice(AESUtil.encrypt(price));
            productRepository.save(product);
            return "Product updated successfully.";
        } else {
            return "Product not found.";
        }
    }

    /**
     * Delete a product.
     * Example: POST /products/delete?id=1
     */
    @PostMapping("/delete")
    public String deleteProduct(@RequestParam Long id, HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "Please login first.";
        }
        productRepository.deleteById(id);
        return "Product deleted successfully.";
    }
}
