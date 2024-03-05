package producer;

public interface OrderService {
	void confirmOrder();
	void pickupOrder(OrderServiceImpl order);
}
