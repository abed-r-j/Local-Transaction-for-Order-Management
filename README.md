# Order Management System

## Hibernate and Spring Boot:

. Used Spring Boot with Hibernate for ORM

. Leveraged Spring Data JPA repositories

. Used H2 in-memory database for simplicity


## Entities:

. Order and Inventory entities with JPA annotations

. Mapped to database tables

. Includes getters, setters, and constructors


## Transactions:

. Used @Transactional annotations for local transactions

. Separate methods for updating accounting_id and payment_id

. Each transaction is atomic and follows ACID principles


## Repositories:

. JpaRepository for database operations

. Provides CRUD functionality out of the box


## Service Layer:

. OrderManagementService contains business logic

. Methods for creating orders, updating order details

. Checks inventory before creating orders

. Handles transaction management


## Main Application:

. Demonstrates usage of the order management system

. Shows order creation and updates



## Dependencies Required:

. Spring Boot Starter Data JPA

. Spring Boot Starter Web

. H2 Database

. Hibernate Core

## How to Run:

. Set up a Spring Boot project

. Add the mentioned dependencies

. Copy these files into your project structure

. Run the OrderManagementApplication
