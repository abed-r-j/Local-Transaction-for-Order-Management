package com.orderms.entity;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "orders")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "accounting_id")
    private Long accountingId;

    @Column(name = "payment_id")
    private Long paymentId;

    @Column(name = "order_status")
    private String orderStatus;

    // Constructors
    public Order() {}

    public Order(Long productId, Integer quantity) {
        this.productId = productId;
        this.quantity = quantity;
        this.orderStatus = "CONFIRMED";
    }

    // Getters and Setters
    public Long getOrderId() { return orderId; }
    public void setOrderId(Long orderId) { this.orderId = orderId; }

    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public Long getAccountingId() { return accountingId; }
    public void setAccountingId(Long accountingId) { this.accountingId = accountingId; }

    public Long getPaymentId() { return paymentId; }
    public void setPaymentId(Long paymentId) { this.paymentId = paymentId; }

    public String getOrderStatus() { return orderStatus; }
    public void setOrderStatus(String orderStatus) { this.orderStatus = orderStatus; }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", productId=" + productId +
                ", quantity=" + quantity +
                ", accountingId=" + accountingId +
                ", paymentId=" + paymentId +
                ", orderStatus='" + orderStatus + '\'' +
                '}';
    }
}