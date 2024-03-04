package producer;

import java.util.Calendar;
import java.util.Scanner;

public class PaymentServiceImpl implements PaymentService {

	private Scanner scanner;
	private String orderId;
	private Double amount;

	public PaymentServiceImpl(String orderId, Double amount) {
		super();
		this.orderId = orderId;
		this.amount = amount;
		this.scanner = new Scanner(System.in);
	}

	public String getOrderId() {
		return orderId;
	}

	public Double getAmount() {
		return amount;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Override
	public boolean processPayment(String paymentMethod, double amount) {
		try {
			System.out.println();
			System.out.println("Processing payment using " + paymentMethod);

			if (paymentMethod.equals("Cash")) {
				return true; // Return true for successful cash payment
			}

			// Prompt user for credit/debit card details
			System.out.print("Enter Card Number: ");
			String cardNumber = scanner.nextLine();
			if (!isValidCardNumber(cardNumber)) {
				System.out.println("Invalid Card Number. Please enter a valid 16-digit card number.");
				return false;
			}

			System.out.print("Enter Expiration Date (MM/YY): ");
			String expirationDate = scanner.nextLine();
			if (!isValidExpirationDate(expirationDate)) {
				System.out
						.println("Invalid Expiration Date. Please use the format MM/YY and ensure it's a future date.");
				return false;
			}

			System.out.print("Enter CVV: ");
			String cvv = scanner.nextLine();
			if (!isValidCVV(cvv)) {
				System.out.println("Invalid CVV. CVV must be a 3-digit number.");
				return false;
			}

			// Validate card (For simplicity, let's assume all cards are valid for now)
			System.out.println("Card details are correct.");
			return true;

		} catch (Exception e) {
			System.out.println("An error occurred while processing payment: " + e.getMessage());
			return false;
		}
	}

	private boolean isValidCardNumber(String cardNumber) {
		// Validate card number using Luhn algorithm
		int sum = 0;
		boolean alternate = false;
		for (int i = cardNumber.length() - 1; i >= 0; i--) {
			int digit = Character.getNumericValue(cardNumber.charAt(i));
			if (alternate) {
				digit *= 2;
				if (digit > 9) {
					digit -= 9;
				}
			}
			sum += digit;
			alternate = !alternate;
		}
		return (sum % 10 == 0);
	}

	private boolean isValidExpirationDate(String expirationDate) {
		// Validate expiration date format and ensure it's a future date
		// MM/YY format
		if (!expirationDate.matches("^(0[1-9]|1[0-2])/(\\d{2})$")) {
			return false;
		}

		String[] parts = expirationDate.split("/");
		int month = Integer.parseInt(parts[0]);
		int year = Integer.parseInt(parts[1]);
		int currentYear = Calendar.getInstance().get(Calendar.YEAR) % 100; // Get last two digits of current year
		int currentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1; // Month is zero-based
		if (year > currentYear || (year == currentYear && month >= currentMonth)) {
			return true;
		} else {
			return false;
		}
	}

	private boolean isValidCVV(String cvv) {
		// CVV must be a 3-digit number
		return cvv.matches("^\\d{3}$");
	}
}
