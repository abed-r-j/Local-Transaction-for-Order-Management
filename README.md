# Local Transaction for Order Management

## Overview

This project demonstrates how to manage local transactions in an order management system using Spring and Hibernate/JPA.

## Features

1. **Order Entity**: Building an `Order` entity class with required fields.
2. **Transaction Service**: Building a transaction service to handle local transactions.
3. **Methods Implementation**: Implementing methods for updating `accounting_id` and `payment_id` in separate transactions.
4. **Entity Management**: Using Hibernate/JPA for entity management.
5. **Transaction Rollbacks**: Handling transaction rollbacks in case of errors.

Each method is annotated with `@Transactional`, ensuring that each update occurs in its own transaction. If an error occurs during either update, that specific transaction will be rolled back independently.

## Configuration

Add the following configuration in `application.properties`:

```properties
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```

## Entity Class

```java
import javax.persistence.*;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String accountingId;
    private String paymentId;
    
    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getAccountingId() { return accountingId; }
    public void setAccountingId(String accountingId) { this.accountingId = accountingId; }
    
    public String getPaymentId() { return paymentId; }
    public void setPaymentId(String paymentId) { this.paymentId = paymentId; }
}
```

## Transaction Service

```java
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class OrderTransactionService {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    @Transactional
    public void updateAccountingId(Long orderId, String accountingId) {
        Order order = entityManager.find(Order.class, orderId);
        if (order != null) {
            order.setAccountingId(accountingId);
            entityManager.merge(order);
        } else {
            throw new RuntimeException("Order not found");
        }
    }
    
    @Transactional
    public void updatePaymentId(Long orderId, String paymentId) {
        Order order = entityManager.find(Order.class, orderId);
        if (order != null) {
            order.setPaymentId(paymentId);
            entityManager.merge(order);
        } else {
            throw new RuntimeException("Order not found");
        }
    }
}
```

## Example Usage

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderUpdateExample {
    
    @Autowired
    private OrderTransactionService orderTransactionService;
    
    public void updateOrder(Long orderId) {
        try {
            // First transaction - update accounting ID
            orderTransactionService.updateAccountingId(orderId, "ACC-" + System.currentTimeMillis());
            
            // Second transaction - update payment ID
            orderTransactionService.updatePaymentId(orderId, "PAY-" + System.currentTimeMillis());
        } catch (Exception e) {
            // Handle exceptions
            e.printStackTrace();
        }
    }
}
```

---
