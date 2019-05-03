package stripe;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.Subscription;
import org.junit.jupiter.api.Test;
import stripe.*;

import java.util.List;

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
		assert verify.equals(testCard.getId());
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
		
		double amount = testCard.getAmount();
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
		for (int i = 0; i < 10; i++) {
			String charge = testCard.charge();
			assert charge.equals("");
					
			double amount = testCard.getAmount();
			assert amount >= 0.50;
		}
	}
	
	@Test
	public void testAutomaticVerifyCorrect() {
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
					
		double amount = testCard.getAmount();
		assert amount >= 0.50;

        assert testCard.verifyCharge(amount);
	}

	@Test
	public void testAutomaticVerifyIncorrect() {
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

		double amount = testCard.getAmount();
		assert amount >= 0.50;

		assert !testCard.verifyCharge(0.31);
	}

	@Test
	public void testChargeBanned() {
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

		testCard.setBanned(true);
		String charge = testCard.charge();
		assert charge.equals("Credit card is banned");
	}

	@Test
	public void testSubscriptionSetting() {
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

		testCard.setSubscription(true);

		try {
			Subscription sub = Subscription.retrieve(testCard.subscriptionId);
			assert sub != null;
			assert sub.getPlan().getNickname().equals("membersOnly");
			assert sub.getPlan().getAmount() >= 0.50;
		} catch(StripeException e) {
			// Generic Stripe exception
		}
	}

	@Test
	public void testSubscriptionCancel() {
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

		testCard.setSubscription(true);
		testCard.setSubscription(false);

		try {
			// No existing subscriptions
			assert Subscription.retrieve(testCard.subscriptionId) == null;
			assert testCard.subscriptionId == null;
		} catch(StripeException e) {
			// Generic Stripe exception
		}
	}
}