package stripe;

import java.util.HashMap;
import java.util.Map;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.Token;

public class StripeCreditCard implements CreditCard {

	/**
	 * The email of the associated member
	 */
	public String email;
	
	/**
	 * The randomized amount to be charged to a member's credit card
	 */
	private int amount;
	
	/**
	 * Flag indicating whether this card is banned or not (possible if "Referred for Deletion" by owner)
	 */
	private boolean isBanned;
	
	/**
	 * The Stripe ID of the member
	 */
	private String stripeID;
	
	/**
	 * The newMember who's credit card information we're referencing
	 */
	private Customer newMember;
	
	/**
	 * Constructor which takes in a member's email
	 * @param email the email of the member
	 */
	public StripeCreditCard(String email) {
		Stripe.apiKey = "sk_test_gCabH088eiNoFnUbVBwfKCLV00p4slRZXy";
		this.email = email;
		this.isBanned = false;
		
		// Map to allow Stripe to store customer details
    	Map<String, Object> customerParameters = new HashMap<String, Object>();
    	
    	// Add the member's email to the parameters
    	customerParameters.put("email", email);
    	
    	// Try catch to avoid throwing an exception
    	try {
			Customer newMember = Customer.create(customerParameters);
			this.newMember = newMember;
			this.stripeID = newMember.getId();
		} catch (StripeException e) {
			// Should never get here TEST THIS
		}
	}
	
	/**
	 * Retrieves the amount charged to the corresponding member for verification
	 * @return the randomized amount we charge to the member
	 */
	@Override
	public int getAmount() {
		return amount;
	}
	
	/**
	 * Creates a Stripe Charge object which stores the details of currency and amount charged to a customer
	 * @param newCustomer
	 * @return a new Stripe Charge object storing the details of the charge (amount, customer, currency type)
	 * @throws StripeException
	 */
	private Charge generateCharge() {
		// Generate random amount between 20-45 cents
		int amount = (int) (Math.random() * 25 + 20);
		this.amount = amount;
		
		Map<String, Object> chargeParameters = new HashMap<String, Object>();
    	chargeParameters.put("amount", Integer.toString(amount));
    	chargeParameters.put("currency", "usd");
    	chargeParameters.put("customer", newMember.getId());
    	try {
			return Charge.create(chargeParameters);
		} catch (StripeException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Creates a Stripe Token object to store member credit card details (used in charging)
	 * @param newCustomer
	 * @return a new Stripe Token object which stores a member's credit card details
	 * @throws StripeException
	 */
	private Token createCard() {
		// Store the details of a member's card
    	Map<String, Object> cardParameters = new HashMap<String, Object>();
    	
    	// ** NEED TO EITHER HAVE THESE PASSED IN OR LOOKUP FROM THE DATABASE **
    	
    	// Required fields of a valid CC
    	cardParameters.put("number", "4000056655665556");
    	cardParameters.put("exp_month", "01");
    	cardParameters.put("exp_year", "2020");
    	cardParameters.put("cvc", "123");
    	
    	// Create a token which stores the details of the card so that we can charge it
    	Map<String, Object> tokenParameters = new HashMap<String, Object>();
    	tokenParameters.put("card", cardParameters);
    	
    	// Create and return the token
    	Token tok;
		try {
			tok = Token.create(tokenParameters);
			return tok;
		} catch (StripeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Charge a member a randomized amount which will be verified, and return true if the charge is successful
	 * @return a boolean value indicating whether or not a transaction has been successfully processed 
	 */
	@Override
	public boolean charge() {
		Token chargeTok = createCard();
		
		// Link the token object to the customer
        Map<String, Object> source = new HashMap<String, Object>();
    	source.put("source", chargeTok.getId());
    	
    	try {
			newMember.getSources().create(source);
		} catch (StripeException e) {
			// TODO Auto-generated catch block
			// some error handling goes here
		}
		return generateCharge() != null;
	}

	/**
	 * Checks whether the credit card associated with the member is banned
	 */
	@Override
	public boolean isBanned() {
		return isBanned;
	}

	/**
	 * Sets the member associated credit card ban
	 * @param on a boolean indicating whether we're enabling or disabling a credit card ban
	 */
	@Override
	public void setBanned(boolean on) {
		isBanned = on;
	}

	/**
	 * Enables/disables subscription payments for a member which recur every 3 months for verification of identity
	 * @param on a boolean indicating whether we're enabling or disabling subscription payments
	 */
	@Override
	public void setSubscription(boolean on) {
		// TODO Auto-generated method stub
	}

	/**
	 * Verifies the validity of a credit card
	 */
	@Override
	public boolean verify() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Return the Stripe ID of a specific member
	 */
	@Override
	public String getId() {
		return stripeID;
	}
	
}
