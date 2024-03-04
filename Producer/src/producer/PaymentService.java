package producer;

public interface PaymentService {
	boolean processPayment(String paymentMethod, double amount);
}
