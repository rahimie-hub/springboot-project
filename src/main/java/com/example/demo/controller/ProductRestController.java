package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.util.AESUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ProductRestController {

    @Autowired
    private ProductRepository productRepository;

    // Hardcoded API key for mobile access.
    private static final String API_KEY = "123456789";

    /**
     * Returns product details in JSON format.
     * Secured via a header 'X-API-KEY'.
     * Example: GET /api/product/1 with header: X-API-KEY: 123456789
     */
    @GetMapping("/product/{id}")
    public Object getProduct(@PathVariable Long id,
                             @RequestHeader(defaultValue = "123456789",value = "X-API-KEY", required = false) String apiKey,
                             HttpServletResponse response) throws IOException {
        if (apiKey == null || !apiKey.equals(API_KEY)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
            return null;
        }
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            // Decrypt the product price before returning.
            String decryptedPrice = AESUtil.decrypt(product.getEncryptedPrice());
            return new ProductResponse(product.getName(), decryptedPrice);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Product not found");
            return null;
        }
    }

    /**
     * Inner class representing the JSON response structure.
     */
    public static class ProductResponse {
        private String name;
        private String price;
        public ProductResponse(String name, String price) {
            this.name = name;
            this.price = price;
        }
        public String getName() {
            return name;
        }
        public String getPrice() {
            return price;
        }
    }
}
