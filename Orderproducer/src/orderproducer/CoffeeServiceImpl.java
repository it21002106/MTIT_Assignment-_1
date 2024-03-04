package orderproducer;


import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class CoffeeServiceImpl implements CoffeeService, BundleActivator {

    @Override
    public void start(BundleContext context) throws Exception {
        System.out.println("Coffee Producer Bundle started.");
        context.registerService(CoffeeService.class.getName(), new CoffeeServiceImpl(), null);
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        System.out.println("Coffee Producer Bundle stopped.");
    }

    @Override
    public String getCoffeeMenu() {
        StringBuilder menu = new StringBuilder();
        menu.append("Welcome to our coffee selection! \n\n");
        menu.append("*************************************\n");
        menu.append("1. Espresso - Rs.800\n");
        menu.append("2. Latte - Rs.750\n");
        menu.append("3. Cappuccino - Rs.900\n");
        menu.append("*************************************");
        return menu.toString();
    }

    @Override
    public String getSizeMenu() {
        StringBuilder menu = new StringBuilder();
        menu.append("Size Menu:\n");
        menu.append("*************************************\n");
        menu.append("1. Small\n");
        menu.append("2. Regular\n");
        menu.append("3. Large\n");
        menu.append("*************************************");
        return menu.toString();
    }

    @Override
    public double calculatePrice(String coffeeType, String size, int quantity) {
        double basePrice;
        switch (coffeeType) {
            case "Espresso":
                basePrice = 800;
                if (size.equalsIgnoreCase("small")) {
                    basePrice -= 150; 
                } else if (size.equalsIgnoreCase("large")) {
                    basePrice += 200;
                }
                break;
            case "Latte":
                basePrice = 750;
                if (size.equalsIgnoreCase("small")) {
                    basePrice -= 150; 
                } else if (size.equalsIgnoreCase("large")) {
                    basePrice += 200; 
                }
                break;
            case "Cappuccino":
                basePrice = 900;
                if (size.equalsIgnoreCase("small")) {
                    basePrice -= 150; 
                } else if (size.equalsIgnoreCase("large")) {
                    basePrice += 200; 
                }
                break;
            default:
                return 0.0;
        }

        double sizeMultiplier;
        switch (size.toLowerCase()) {
            case "small":
                sizeMultiplier = 1.0;
                break;
            case "regular":
                sizeMultiplier = 1.0;
                break;
            case "large":
                sizeMultiplier = 1.0;
                break;
            default:
                return 0.0; 
        }

        return basePrice * sizeMultiplier * quantity;
    }

	@Override
	public double getTotalAmount() {
		// TODO Auto-generated method stub
		return 0;
	}
}
