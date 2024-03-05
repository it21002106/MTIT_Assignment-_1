package consumer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import producer.OrderService;

import java.util.Scanner;


public class Activator implements BundleActivator {

	private ServiceReference<?> reference;
	private Scanner scanner;

	@Override
	public void start(BundleContext context) throws Exception {
		System.out.println("Start Consumer Bundle!");
		
		//Order Confirmation
		reference = context.getServiceReference(OrderService.class.getName()); 
        OrderService orderService = (OrderService) context.getService(reference);
        
		scanner = new Scanner(System.in);
		startService(orderService);
		
	}    

	@Override
	public void stop(BundleContext context) throws Exception {
		scanner.close();
        System.out.println("Stop Publisher!");
        context.ungetService(reference);
	}
	
	public void startService(OrderService viewOrderService) {
		
	        try {
	            boolean exit = false;
	            while (!exit) {
	                int selection;
	                do {
	                	System.out.println();
	                    System.out.println("**********************************");
	                    System.out.println("Choose the service that you want ");
	                    System.out.println("1 - Order Selection");
	                    System.out.println("2 - Customization");
	                    System.out.println("3 - Payment");
	                    System.out.println("4 - Order Confirmation & Pickup");
	                    System.out.println("0 - Exit");
	                    System.out.println("**********************************");
	                    System.out.println();
	                    System.out.print("Choose Option Number : ");
	                    selection = Integer.parseInt(scanner.nextLine());
	                    

	                    if (selection == 0) {
	                    	System.out.println("\n\tGOODBYE, WELCOME AGAIN!");
	                        System.in.read();
	                    }

	                    if (selection != 1 && selection != 2 && selection != 3 && selection != 4 && selection != 0) {
	                        System.out.println("Please enter a VALID Selection!");
	                        System.out.println();
	                    }
	                } while (selection != 1 && selection != 2 && selection != 3 && selection != 4 && selection != 0);

	                if (selection == 1) {              	
	                	//Order Selection
	                } 
	                
	                if (selection == 2) {
	                	//Customization
	                }
	                
	                if (selection == 3) {              	
	                    //Payment
	                } 
	                
	                if (selection == 4) {              	
	                    do {
	                        viewOrderService.confirmOrder();
	                        System.out.println();
	                        System.out.print("Press 9 to navigate Options : ");
	                    } while (!scanner.nextLine().equals("9"));
	                } 
	            }
	        } catch (Exception e) {
	            System.out.println("An error occurred: " + e.getMessage());
	        }
	    }
	
}