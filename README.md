# Local-Transaction-for-Order-Management

1. Building an Order entity class with required fields
2. Building a transaction service to handle local transactions
3. Implementing methods for updating accounting_id and payment_id in separate transactions
4. Using Hibernate/JPA for entity management
5. Handling transaction rollbacks in case of errors

Each method is annotated with @Transactional, ensuring that each update occurs in its own transaction. If an error occurs during either update, that specific transaction will be rolled back independently.



### Configuration required in application.properties:

  spring.jpa.hibernate.ddl-auto=update
  
  spring.jpa.show-sql=true
  
  spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
