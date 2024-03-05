package producer;

import java.util.Scanner;

import java.util.HashMap;
import java.util.Map;

import java.util.Date;

public class OrderServiceImpl implements OrderService {

	private Map <String, OrderServiceImpl> ordersMap = new HashMap<>();
	
    private Scanner scanner;
  
	private String orderId, paymentId, status;
	double price;
    
    public OrderServiceImpl() {
        this.scanner = new Scanner(System.in);
        this.ordersMap = new HashMap<>();
        // Initialize the orders
        initializeOrders();
    }
    
    private void initializeOrders() {
        // Create OrderServiceImpl objects and store them in the map
        OrderServiceImpl order1 = new OrderServiceImpl("001", "PAY001", "Completed", 560.0);
        OrderServiceImpl order2 = new OrderServiceImpl("002", "PAY002", "Not Paid", 670.0);
        OrderServiceImpl order3 = new OrderServiceImpl("003", "PAY003", "Failed", 890.0);

        ordersMap.put(order1.getOrderId(), order1);
        ordersMap.put(order2.getOrderId(), order2);
        ordersMap.put(order3.getOrderId(), order3);
    }

    //getters
    public String getOrderId() {
		return orderId;
	}

	public String getPaymentId() {
		return paymentId;
	}

	public String getStatus() {
		return status;
	}
	
	public double getPrice() {
		return price;
	}
	
	//setters
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}


	public OrderServiceImpl(String orderId, String paymentId, String status, double price) {
		this.orderId = orderId;
		this.paymentId = paymentId;
		this.status = status;
		this.price = price;
    }
    
	@Override
    public void confirmOrder() {
        try {
        	System.out.println();
        	System.out.println("\n\t VIEW YOUR ORDER CONFIRMATION AND PICKUP");
        	System.out.println();
            System.out.print("\tEnter your Order ID : ");
            String orderId = scanner.nextLine(); // Read the user input
            System.out.println();
            
         // Retrieve the OrderServiceImpl object from the map based on the orderId
            OrderServiceImpl order = ordersMap.get(orderId);

            if (order != null) {
                displayOrderConfirmation(order);
                if (order.getStatus().equals("Completed")) {
                	pickupOrder(order);
                }
            } else {
                System.out.println("\tOrder not found!");
            }
            
        } catch (Exception e) {
            System.out.println("An error occurred while confirming the order: " + e.getMessage());
        }
    }

    @Override
    public void pickupOrder(OrderServiceImpl order) {
    	try {
            // Notify the customer through the self-service interface or designated pickup area
            notifyCustomer(order);
        } catch (Exception e) {
            System.out.println("An error occurred while picking up the order: " + e.getMessage());
        }
    }   
   
    private void displayOrderConfirmation(OrderServiceImpl order) {
        // Fetch order details from the service
    	System.out.println("\t=========================================");
    	System.out.println("\t\t      Order Summery  ");
    	System.out.println("\t=========================================");
    	System.out.println();
    	System.out.println("\t\tOrder ID:       " + order.getOrderId());
        System.out.println("\t\tPayment ID:     " + order.getPaymentId());
        System.out.println("\t\tPayment Status: " + order.getStatus());
        System.out.println();
        System.out.println("\t=========================================");
        System.out.println();

        if(order.getStatus() == "Completed") {
        	System.out.println("\tOrder Confirmed!");
        }
        else if(order.getStatus() == "Failed") {
        	System.out.println("\tSorry, Payment is Failed. Try Again!");
        }
        else {
        	System.out.println("\tComplete the your Payment!");
        }
        System.out.println();
    }

    private void notifyCustomer(OrderServiceImpl order) {
    	// Display "Preparing..." message
        System.out.println("\tPreparing...");
        System.out.println();

        try {
			Thread.sleep(5000);			
			// Schedule a task to display the order ready message after 5 seconds
	        System.out.println("\tOrder ID " + order.getOrderId() + " is ready for pickup.");
	        System.out.println();
	        Thread.sleep(1000);
	        System.out.println("\n\t=====================================================");
	    	System.out.println("\t\t      PRINT YOUR E-RECEIPT  ");
	    	System.out.println("\t======================================================");
	    	System.out.println();
	    	System.out.println("\tTransaction Date & Time : " + new Date());
	    	System.out.println("\tOrder ID                : " + order.getOrderId());
	        System.out.println("\tPayment ID              : " + order.getPaymentId());
	        System.out.println("\tTotal Amout             : " + order.getPrice());
	        System.out.println();
	        System.out.println("\t======================================================");
	        System.out.println();
	        Thread.sleep(1000);
	        rateService();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }
    
    private static void rateService() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\t++++++++++++++++++++++++++++++++++++++++");
        System.out.println("\t+       			       +");
        System.out.println("\t+\t  Enjoying the Service?        +");
        System.out.println("\t+\tWould you mind to rate us?     +");
        System.out.println("\t+       			       +");
        System.out.println("\t+\t\t\u2606 \u2606 \u2606 \u2606 \u2606\t       +");
        System.out.println("\t+       			       +");
        System.out.println("\t++++++++++++++++++++++++++++++++++++++++");
        
        System.out.print("\n\tRate Us (y/n) : ");
        String rate = scanner.nextLine();
        
        if (rate.equalsIgnoreCase("y")){
	        System.out.print("\n\tEnter your rating (0-5): ");
	        int rating = scanner.nextInt();
	        
	        if (rating >= 0 && rating <= 5) {
	            System.out.println("\n\tYour rating: ");
	            System.out.print("\n\t\t\t");
	            for (int i = 0; i < rating; i++) {
	                System.out.print("\u2605 "); // Unicode for a filled star
	            }
	            for (int i = rating; i < 5; i++) {
	                System.out.print("\u2606 "); // Unicode for an empty star
	            }
	            System.out.println();       
	            System.out.println("\n\t\tTHANK YOU FOR THE RATING !!!");
	            System.out.println();
	        } else {
	            System.out.println("\n\tInvalid rating. Please enter a rating between 0 and 5.");
	            System.out.print("\n\tEnter your rating (0-5): ");
		        rating = scanner.nextInt();
	            if (rating >= 0 && rating <= 5) {
		            System.out.println("\n\tYour rating: ");
		            System.out.print("\n\t\t\t");
		            for (int i = 0; i < rating; i++) {
		                System.out.print("\u2605 "); // Unicode for a filled star
		            }
		            for (int i = rating; i < 5; i++) {
		                System.out.print("\u2606 "); // Unicode for an empty star
		            }
		            System.out.println();       
		            System.out.println("\n\t\tTHANK YOU FOR THE RATING !!!");
		            System.out.println();
		        } else {
		            System.out.println("\n\tInvalid rating. Please enter a rating between 0 and 5.");
		        }
	        }
        }else {
        	System.out.println("\n\t\tGOODBYE, WELCOME AGAIN!");
        }
    }
        
    
}