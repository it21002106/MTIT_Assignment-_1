package consumer;

import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import producer.CoffeeCustomizationService;

public class Activator implements BundleActivator {
	private ServiceReference<CoffeeCustomizationService> serviceReference;

    @Override
    public void start(BundleContext context) throws Exception {
        serviceReference = context.getServiceReference(CoffeeCustomizationService.class);
        CoffeeCustomizationService customizationService = context.getService(serviceReference);
        Scanner scanner = new Scanner(System.in);
        try {
            boolean continueOrdering = true;
            while (continueOrdering) {
                System.out.print("Enter the order number: ");
                String orderNumber = scanner.nextLine();
                String coffeeType = identifyCoffeeType(orderNumber);
                
                
                if (coffeeType.equals("Unknown Order")) {
                    System.out.println("Invalid order number. Please start with 'E' for Espresso, 'L' for Latte, or 'C' for Cappuccino.");
                    continue;
                }
                System.out.println("You ordered: " + coffeeType);
                
                String customizationOptions = customizationService.getCustomizationOptions(coffeeType);
                System.out.println("Available customization options: " + customizationOptions);
                
                int maxCustomizationOption = getMaxCustomizationOption(coffeeType);
                System.out.print("Enter the customization option (1-" + maxCustomizationOption + "): ");
                int customizationOption = scanner.nextInt();
                if (customizationOption < 1 || customizationOption > maxCustomizationOption) {
                    System.out.println("Invalid customization option. Please enter a number between 1 and " + maxCustomizationOption + ".");
                    continue;
                }
                
                String customizedCoffee = customizationService.customizeCoffee(orderNumber, customizationOption);
                System.out.println("Customized Coffee: " + customizedCoffee);
                
                scanner.nextLine();
                System.out.print("Are there another order (y/n)? ");
                String continueInput = scanner.nextLine();
                if (!continueInput.equalsIgnoreCase("y")) {
                    continueOrdering = false;
                    System.out.print(" * ThankYou for using our service. Have a nice Day! ");
                }
            }
        } finally {
            scanner.close();
        }
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        context.ungetService(serviceReference);
    }

    private String identifyCoffeeType(String orderNumber) {
        if (orderNumber.startsWith("E")) {
            return "Espresso";
        } else if (orderNumber.startsWith("L")) {
            return "Latte";
        } else if (orderNumber.startsWith("C")) {
            return "Cappuccino";
        } else {
            return "Unknown Order";
        }
    }
    
    private int getMaxCustomizationOption(String coffeeType) {
        switch (coffeeType) {
            case "Espresso":
                return 6; 
            case "Latte":
                return 4;
            case "Cappuccino":
                return 5; 
            default:
                return 0;
        }
    }
}

