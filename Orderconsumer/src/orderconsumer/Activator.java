package orderconsumer;

import orderproducer.CoffeeService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import java.util.Scanner;

public class Activator implements BundleActivator {
    private BundleContext context;
    private double totalAmount;

    @Override
    public void start(BundleContext context) throws Exception {
        this.context = context;
        System.out.println("Consumer Bundle started.");
        ServiceReference<CoffeeService> serviceRef = context.getServiceReference(CoffeeService.class);
        if (serviceRef != null) {
            CoffeeService coffeeService = context.getService(serviceRef);
            Scanner scanner = new Scanner(System.in);

            StringBuilder selectedCoffees = new StringBuilder("\nSelected coffees:\n\n");

            while (true) {
                System.out.print("\n\n");
                System.out.println(coffeeService.getCoffeeMenu());
                System.out.print("Please choose your desired coffee by entering the number: ");

                int choice;
                try {
                    choice = scanner.nextInt();
                } catch (Exception e) {
                    System.out.println("Invalid input. Exiting...");
                    break;
                }

                String coffeeType;
                switch (choice) {
                    case 1:
                        coffeeType = "Espresso";
                        break;
                    case 2:
                        coffeeType = "Latte";
                        break;
                    case 3:
                        coffeeType = "Cappuccino";
                        break;
                    default:
                        System.out.println("Invalid choice. Please select a valid coffee.");
                        continue;
                }

                System.out.print("\n\n");
                System.out.println("Selected coffee: " + coffeeType);
                System.out.print("\n\n");

                String size;
                while (true) {
                    System.out.println(coffeeService.getSizeMenu());
                    System.out.print("\n\n");
                    System.out.print("Enter the number to select your desired size: ");
                    int sizeChoice;
                    try {
                        sizeChoice = scanner.nextInt();
                    } catch (Exception e) {
                        System.out.println("Invalid input. Exiting...");
                        return;
                    }

                    switch (sizeChoice) {
                        case 1:
                            size = "Small";
                            break;
                        case 2:
                            size = "Regular";
                            break;
                        case 3:
                            size = "Large";
                            break;
                        default:
                            System.out.println("Invalid choice. Please select a valid size.");
                            continue;
                    }
                    break;
                }

                System.out.print("\n\n");
                System.out.print("Enter the number of coffee orders: ");
                int quantity = scanner.nextInt();

                double totalPrice = coffeeService.calculatePrice(coffeeType, size, quantity);
                if (totalPrice > 0) {
                    totalAmount += totalPrice;
                    selectedCoffees.append("Coffee: ").append(coffeeType).append("\n Size: ").append(size).append("\nQuantity: ").append(quantity).append("\n\n");
                } else {
                    System.out.println("Invalid coffee type or size selected. Exiting...");
                    break;
                }

                System.out.println("\nSo far order price: Rs. " + totalAmount);
                System.out.println("\nDo you want another coffee? (yes/no)");
                String anotherChoice = scanner.next();
                if (!anotherChoice.equalsIgnoreCase("y")) {
                    break;
                }
            }

            System.out.println(selectedCoffees);
            System.out.println("\nTotal amount to be paid: Rs. " + totalAmount);
            System.out.println("\nPress Enter to exit...");
            System.in.read();
            context.getBundle(0).stop();
        } else {
            System.out.println("Coffee Service not available.");
            System.out.println("Press Enter to exit...");
            System.in.read();
            context.getBundle(0).stop();
        }
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        System.out.println("Consumer Bundle stopped.");
    }
}


