package stripe;

import org.junit.Test;

import com.stripe.Stripe;

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
