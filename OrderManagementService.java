package com.orderms.service;

import com.orderms.entity.Order;
import com.orderms.entity.Inventory;
import com.orderms.repository.OrderRepository;
import com.orderms.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.List;

@Service
public class OrderManagementService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    @Transactional
    public void createOrder(Long productId, Integer quantity) {
        // Check inventory
        Optional<Inventory> inventoryOpt = inventoryRepository.findById(productId);
        if (inventoryOpt.isEmpty()) {
            throw new RuntimeException("Product does not exist in inventory.");
        }

        Inventory inventory = inventoryOpt.get();
        if (inventory.getStock() < quantity) {
            throw new RuntimeException("Not enough stock available.");
        }

        // Update inventory
        inventory.setStock(inventory.getStock() - quantity);
        inventoryRepository.save(inventory);

        // Create order
        Order order = new Order(productId, quantity);
        orderRepository.save(order);
    }

    @Transactional
    public void updateAccountingId(Long orderId, Long accountingId) {
        Optional<Order> orderOpt = orderRepository.findById(orderId);
        if (orderOpt.isPresent()) {
            Order order = orderOpt.get();
            order.setAccountingId(accountingId);
            orderRepository.save(order);
        } else {
            throw new RuntimeException("Order not found");
        }
    }

    @Transactional
    public void updatePaymentId(Long orderId, Long paymentId) {
        Optional<Order> orderOpt = orderRepository.findById(orderId);
        if (orderOpt.isPresent()) {
            Order order = orderOpt.get();
            order.setPaymentId(paymentId);
            orderRepository.save(order);
        } else {
            throw new RuntimeException("Order not found");
        }
    }

    public List<Order> showOrders() {
        return orderRepository.findAll();
    }

    public List<Inventory> showInventory() {
        return inventoryRepository.findAll();
    }
}