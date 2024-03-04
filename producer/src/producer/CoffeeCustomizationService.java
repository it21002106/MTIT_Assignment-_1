package producer;

public interface CoffeeCustomizationService {
	String customizeCoffee(String orderNumber, int customizationOption);

	String getCustomizationOptions(String coffeeType);
}
