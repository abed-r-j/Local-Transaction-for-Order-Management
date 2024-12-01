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