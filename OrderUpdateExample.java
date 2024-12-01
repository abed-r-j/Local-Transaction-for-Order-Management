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