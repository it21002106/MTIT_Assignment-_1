package producer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import java.util.Scanner;

public class Activator implements BundleActivator {

	private ServiceRegistration<PaymentService> registration;
	private Scanner scanner;

	PaymentServiceImpl payment = new PaymentServiceImpl("001", 1200.00);

	@Override
	public void start(BundleContext context) throws Exception {
		System.out.println("Start Payment Processor Bundle!");
		PaymentService paymentService = new PaymentServiceImpl("002", 950.00);
		registration = context.registerService(PaymentService.class, paymentService, null);
		scanner = new Scanner(System.in);
		startPaymentProcessing(paymentService);
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		registration.unregister();
		scanner.close();
		System.out.println("Stop Payment Processor Bundle!");
	}

	public void startPaymentProcessing(PaymentService paymentService) {
		try {
			boolean exit = false;
			while (!exit) {
				int selection;
				do {
					System.out.println();
					System.out.println("Order ID: " + payment.getOrderId());
					System.out.println("Amount: Rs. " + payment.getAmount());
					System.out.println();
					System.out.println("**********************************");
					System.out.println("Choose the payment method:");
					System.out.println("1 - Credit Card");
					System.out.println("2 - Debit card");
					System.out.println("3 - Cash");
					System.out.println("0 - Exit");
					System.out.println("**********************************");
					System.out.println();
					System.out.print("Choose Option Number : ");
					selection = Integer.parseInt(scanner.nextLine());

					if (selection == 0) {
						exit = true; // Exit the loop when 0 is entered
					} else if (selection == 1 || selection == 2) {
						// Process payment for Credit Card or Debit Card
						boolean paymentStatus = paymentService
								.processPayment((selection == 1) ? "Credit Card" : "Debit Card", payment.getAmount());
						if (paymentStatus) {
							System.out.println("Payment Successful!");
							System.out.println("Exit..");
							System.in.read();
							// Update selection to exit the loop
							selection = 0;
						} else {
							System.out.println("Payment failed. Please try again.");
						}
					} else if (selection == 3) {
						// Process payment for Cash
						System.out.println("Payment Successful!");
						// Update selection to exit the loop
						System.out.println("Exit...");
						System.in.read();
						selection = 0;
					} else {
						System.out.println("Please enter a VALID Selection!");
					}
				} while (selection != 0); // Continue the loop until exit option is chosen
			}
		} catch (Exception e) {
			System.out.println("An error occurred: " + e.getMessage());
		}
	}
}
