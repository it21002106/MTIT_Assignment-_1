package orderproducer;

public interface CoffeeService {
    String getCoffeeMenu(); 
    String getSizeMenu(); 
    double calculatePrice(String coffeeType, String size, int quantity);
    double getTotalAmount(); 
}
