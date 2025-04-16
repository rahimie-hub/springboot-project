package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    // The product price is stored as an encrypted string
    @Column(nullable = false)
    private String encryptedPrice;

    // Getters and setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEncryptedPrice() {
        return encryptedPrice;
    }
    public void setEncryptedPrice(String encryptedPrice) {
        this.encryptedPrice = encryptedPrice;
    }
}
