package com.orderms.entity;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "inventory")
public class Inventory implements Serializable {
    @Id
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "stock")
    private Integer stock;

    // Constructors
    public Inventory() {}

    public Inventory(Long productId, String productName, Integer stock) {
        this.productId = productId;
        this.productName = productName;
        this.stock = stock;
    }

    // Getters and Setters
    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }
}