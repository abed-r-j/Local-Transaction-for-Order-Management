package com.orderms;

import com.orderms.service.OrderManagementService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class OrderManagementApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(OrderManagementApplication.class, args);
        
        OrderManagementService orderManagementService = context.getBean(OrderManagementService.class);
        
        // Add inventory
        Inventory laptop = new Inventory(1L, "Laptop", 10);
        orderManagementService.inventoryRepository.save(laptop);

        // Create an order
        orderManagementService.createOrder(1L, 2);

        System.out.println("\nOrder Records Before Updates:");
        orderManagementService.showOrders().forEach(System.out::println);

        // Update accounting_id and payment_id in separate transactions
        System.out.println("\nUpdating Order Records:");
        orderManagementService.updateAccountingId(1L, 101L);  // Update accounting_id
        orderManagementService.updatePaymentId(1L, 202L);     // Update payment_id

        System.out.println("\nOrder Records After Updates:");
        orderManagementService.showOrders().forEach(System.out::println);
    }
}