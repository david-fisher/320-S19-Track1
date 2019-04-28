package stripe;

import java.util.Scanner;

import org.junit.Test;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;

public class StripeCreditCardTest {
	
	@Test
	public void testCreateCard() {
		// Define the Stripe key we're using
		Stripe.apiKey = "sk_test_gCabH088eiNoFnUbVBwfKCLV00p4slRZXy";
		
		// Necessary fields
		String email = "test@gmail.com";
		String cardNum = "4000056655665556";
		String zipCode = "00000";
		String cvv = "123";
		String exp_month = "10";
		String exp_year = "2020";
		
		// Create the test card with the information above
		StripeCreditCard testCard = new StripeCreditCard(email, cardNum, zipCode,
														 cvv, exp_month, exp_year);
		
		System.out.println("Generated member with Stripe Id: " + testCard.getId());
		System.out.println();
		
		assert testCard.getId() != null;
	}
	
	@Test
	public void testVerifyValid() {
		// Define the Stripe key we're using
		Stripe.apiKey = "sk_test_gCabH088eiNoFnUbVBwfKCLV00p4slRZXy";
				
		// Necessary fields
		String email = "test@gmail.com";
		String cardNum = "4000056655665556";
		String zipCode = "00000";
		String cvv = "123";
		String exp_month = "10";
		String exp_year = "2020";
				
		// Create the test card with the information above
		StripeCreditCard testCard = new StripeCreditCard(email, cardNum, zipCode,
														 cvv, exp_month, exp_year);
		assert testCard.getId() != null;
		
		String verify = testCard.verify();
		assert verify != null;
		assert verify == testCard.getId();
	}
	
	@Test
	public void testVerifyInvalidLongCCNum() {
		// Define the Stripe key we're using
		Stripe.apiKey = "sk_test_gCabH088eiNoFnUbVBwfKCLV00p4slRZXy";
						
		// Necessary fields
		String email = "test@gmail.com";
		String cardNum = "4000056655665556104362943";
		String zipCode = "00000";
		String cvv = "123";
		String exp_month = "10";
		String exp_year = "2020";
						
		// Create the test card with the information above
		StripeCreditCard testCard = new StripeCreditCard(email, cardNum, zipCode,
														 cvv, exp_month, exp_year);
		assert testCard.getId() != null;
				
		String verify = testCard.verify();
		assert verify == null;
	}
	
	@Test
	public void testVerifyInvalidShortCCNum() {
		// Define the Stripe key we're using
		Stripe.apiKey = "sk_test_gCabH088eiNoFnUbVBwfKCLV00p4slRZXy";
						
		// Necessary fields
		String email = "test@gmail.com";
		String cardNum = "40000";
		String zipCode = "00000";
		String cvv = "123";
		String exp_month = "10";
		String exp_year = "2020";
						
		// Create the test card with the information above
		StripeCreditCard testCard = new StripeCreditCard(email, cardNum, zipCode,
														 cvv, exp_month, exp_year);
		assert testCard.getId() != null;
				
		String verify = testCard.verify();
		assert verify == null;
	}
	
	@Test
	public void testVerifyInvalidCCNum() {
		// Define the Stripe key we're using
		Stripe.apiKey = "sk_test_gCabH088eiNoFnUbVBwfKCLV00p4slRZXy";
						
		// Necessary fields
		String email = "test@gmail.com";
		String cardNum = "0000000000000000";
		String zipCode = "00000";
		String cvv = "123";
		String exp_month = "10";
		String exp_year = "2020";
						
		// Create the test card with the information above
		StripeCreditCard testCard = new StripeCreditCard(email, cardNum, zipCode,
														 cvv, exp_month, exp_year);
		assert testCard.getId() != null;
				
		String verify = testCard.verify();
		assert verify == null;
	}
	
	@Test
	public void testChargeSuccessful() {
		// Define the Stripe key we're using
		Stripe.apiKey = "sk_test_gCabH088eiNoFnUbVBwfKCLV00p4slRZXy";
				
		// Necessary fields
		String email = "test@gmail.com";
		String cardNum = "4000056655665556";
		String zipCode = "00000";
		String cvv = "123";
		String exp_month = "10";
		String exp_year = "2020";
				
		// Create the test card with the information above
		StripeCreditCard testCard = new StripeCreditCard(email, cardNum, zipCode,
														 cvv, exp_month, exp_year);

		String charge = testCard.charge();
		assert charge.equals("");
		System.out.print("Charge success! Amount: ");
		
		double amount = testCard.getAmount();
		System.out.println("$" + amount);
		System.out.println();
		assert amount >= 0.50;
	}
	
	@Test
	public void testChargeFailureCCNumber() {
		// Define the Stripe key we're using
		Stripe.apiKey = "sk_test_gCabH088eiNoFnUbVBwfKCLV00p4slRZXy";
				
		// Necessary fields
		String email = "test@gmail.com";
		String cardNum = "40000566556655234874926";
		String zipCode = "24340";
		String cvv = "770";
		String exp_month = "10";
		String exp_year = "2020";
				
		// Create the test card with the information above
		StripeCreditCard testCard = new StripeCreditCard(email, cardNum, zipCode,
														 cvv, exp_month, exp_year);

		String charge = testCard.charge();
		assert charge.equals("incorrect_number");
	}
	
	@Test
	public void testChargeFailureCVV() {
		// Define the Stripe key we're using
		Stripe.apiKey = "sk_test_gCabH088eiNoFnUbVBwfKCLV00p4slRZXy";
				
		// Necessary fields
		String email = "test@gmail.com";
		String cardNum = "4000056655665556";
		String zipCode = "24340";
		String cvv = "7";
		String exp_month = "10";
		String exp_year = "2020";
				
		// Create the test card with the information above
		StripeCreditCard testCard = new StripeCreditCard(email, cardNum, zipCode,
														 cvv, exp_month, exp_year);

		String charge = testCard.charge();
		assert charge.equals("invalid_cvc");
	}
	
	@Test
	public void testChargeFailureExpMonth() {
		// Define the Stripe key we're using
		Stripe.apiKey = "sk_test_gCabH088eiNoFnUbVBwfKCLV00p4slRZXy";
				
		// Necessary fields
		String email = "test@gmail.com";
		String cardNum = "4000056655665556";
		String zipCode = "24340";
		String cvv = "770";
		String exp_month = "100";
		String exp_year = "2020";
				
		// Create the test card with the information above
		StripeCreditCard testCard = new StripeCreditCard(email, cardNum, zipCode,
														 cvv, exp_month, exp_year);

		String charge = testCard.charge();
		assert charge.equals("invalid_expiry_month");
	}
	
	@Test
	public void testChargeFailureExpYear() {
		// Define the Stripe key we're using
		Stripe.apiKey = "sk_test_gCabH088eiNoFnUbVBwfKCLV00p4slRZXy";
				
		// Necessary fields
		String email = "test@gmail.com";
		String cardNum = "4000056655665556";
		String zipCode = "24340";
		String cvv = "770";
		String exp_month = "10";
		String exp_year = "20200";
				
		// Create the test card with the information above
		StripeCreditCard testCard = new StripeCreditCard(email, cardNum, zipCode,
														 cvv, exp_month, exp_year);

		String charge = testCard.charge();
		assert charge.equals("invalid_expiry_year");
	}
	
	@Test
	public void testCreateCustomer() {
		// Define the Stripe key we're using
		Stripe.apiKey = "sk_test_gCabH088eiNoFnUbVBwfKCLV00p4slRZXy";
				
		// Necessary fields
		String email = "test@gmail.com";
		String cardNum = "4000056655665556";
		String zipCode = "00000";
		String cvv = "123";
		String exp_month = "10";
		String exp_year = "2020";
				
		// Create the test card with the information above
		StripeCreditCard testCard = new StripeCreditCard(email, cardNum, zipCode,
														 cvv, exp_month, exp_year);
		try {
			Customer newMember = Customer.retrieve(testCard.getId());
			assert newMember != null;
		} catch (StripeException e) {
			
		}
	}
	
	@Test
	public void testRandomGeneratedCharges() {
		// Define the Stripe key we're using
		Stripe.apiKey = "sk_test_gCabH088eiNoFnUbVBwfKCLV00p4slRZXy";
						
		// Necessary fields
		String email = "test@gmail.com";
		String cardNum = "4000056655665556";
		String zipCode = "00000";
		String cvv = "123";
		String exp_month = "10";
		String exp_year = "2020";
						
		// Create the test card with the information above
		StripeCreditCard testCard = new StripeCreditCard(email, cardNum, zipCode,
														 cvv, exp_month, exp_year);
		
		System.out.println("Processing randomized charges...");
		for (int i = 0; i < 10; i++) {
			String charge = testCard.charge();
			assert charge.equals("");
			System.out.print("Charge success! Amount: ");
					
			double amount = testCard.getAmount();
			System.out.println("$" + amount);
			assert amount >= 0.50;
		}
	}
	
	@Test
	public void testManualVerify() {
		// Define the Stripe key we're using
		Stripe.apiKey = "sk_test_gCabH088eiNoFnUbVBwfKCLV00p4slRZXy";
						
		// Necessary fields
		String email = "test@gmail.com";
		String cardNum = "4000056655665556";
		String zipCode = "00000";
		String cvv = "123";
		String exp_month = "10";
		String exp_year = "2020";
						
		// Create the test card with the information above
		StripeCreditCard testCard = new StripeCreditCard(email, cardNum, zipCode,
														 cvv, exp_month, exp_year);
		
		System.out.println("Processing a charge");
		
		String charge = testCard.charge();
		assert charge.equals("");
		System.out.print("Charge success! Amount: ");
					
		double amount = testCard.getAmount();
		System.out.println("$" + amount);
		assert amount >= 0.50;
		
		Scanner scan = new Scanner(System.in);
        System.out.println("Please verify the amount charged to your credit card...");
        System.out.println("Input format should be 0.XX...");

        // Read in the amount charged manually... match above value to pass
        double amt = scan.nextDouble();

        // Closing Scanner after the use
        scan.close();
        
        assert amt == testCard.getAmount();
	}
	
	@Test
	public void testVerifyInvalidLongCCNum() {
		// Define the Stripe key we're using
		Stripe.apiKey = "sk_test_gCabH088eiNoFnUbVBwfKCLV00p4slRZXy";
						
		// Necessary fields
		String email = "test@gmail.com";
		String cardNum = "4000056655665556104362943";
		String zipCode = "00000";
		String cvv = "123";
		String exp_month = "10";
		String exp_year = "2020";
						
		// Create the test card with the information above
		StripeCreditCard testCard = new StripeCreditCard(email, cardNum, zipCode,
														 cvv, exp_month, exp_year);
		assert testCard.getId() != null;
				
		String verify = testCard.verify();
		assert verify == null;
	}
	
	@Test
	public void testVerifyInvalidShortCCNum() {
		// Define the Stripe key we're using
		Stripe.apiKey = "sk_test_gCabH088eiNoFnUbVBwfKCLV00p4slRZXy";
						
		// Necessary fields
		String email = "test@gmail.com";
		String cardNum = "40000";
		String zipCode = "00000";
		String cvv = "123";
		String exp_month = "10";
		String exp_year = "2020";
						
		// Create the test card with the information above
		StripeCreditCard testCard = new StripeCreditCard(email, cardNum, zipCode,
														 cvv, exp_month, exp_year);
		assert testCard.getId() != null;
				
		String verify = testCard.verify();
		assert verify == null;
	}
	
	@Test
	public void testVerifyInvalidCCNum() {
		// Define the Stripe key we're using
		Stripe.apiKey = "sk_test_gCabH088eiNoFnUbVBwfKCLV00p4slRZXy";
						
		// Necessary fields
		String email = "test@gmail.com";
		String cardNum = "0000000000000000";
		String zipCode = "00000";
		String cvv = "123";
		String exp_month = "10";
		String exp_year = "2020";
						
		// Create the test card with the information above
		StripeCreditCard testCard = new StripeCreditCard(email, cardNum, zipCode,
														 cvv, exp_month, exp_year);
		assert testCard.getId() != null;
				
		String verify = testCard.verify();
		assert verify == null;
	}
	
	@Test
	public void testChargeSuccessful() {
		// Define the Stripe key we're using
		Stripe.apiKey = "sk_test_gCabH088eiNoFnUbVBwfKCLV00p4slRZXy";
				
		// Necessary fields
		String email = "test@gmail.com";
		String cardNum = "4000056655665556";
		String zipCode = "00000";
		String cvv = "123";
		String exp_month = "10";
		String exp_year = "2020";
				
		// Create the test card with the information above
		StripeCreditCard testCard = new StripeCreditCard(email, cardNum, zipCode,
														 cvv, exp_month, exp_year);

		boolean charge = testCard.charge();
		assert charge == true;
		System.out.print("Charge success! Amount: ");
		
		int amount = testCard.getAmount();
		System.out.print(amount);
		assert amount >= 50;
	}
	
	@Test
	public void testChargeFailure() {
		// Define the Stripe key we're using
		Stripe.apiKey = "sk_test_gCabH088eiNoFnUbVBwfKCLV00p4slRZXy";
				
		// Necessary fields
		String email = "somerandommessgoesherebecauseitsatest";
		String cardNum = "40000566556655234874926";
		String zipCode = "243483";
		String cvv = "77";
		String exp_month = "10001";
		String exp_year = "42894829";
				
		// Create the test card with the information above
		StripeCreditCard testCard = new StripeCreditCard(email, cardNum, zipCode,
														 cvv, exp_month, exp_year);

		boolean charge = testCard.charge();
		assert charge == false;
	}
	
	
}
