package stripe;

import java.util.HashMap;
import java.util.Map;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.Subscription;
import com.stripe.model.Token;

public class StripeCreditCard implements CreditCard {

	/**
	 * The email of the associated member
	 */
	private String email;
	
	/**
	 * The credit card number of the associated member
	 */
	private String cardNumber;
	
	/**
	 * The cvv code of the associated credit card
	 */
	private String cvv;
	
	/**
	 * The expiration month of the associated credit card
	 */
	private String exp_month;
	
	/**
	 * The expiration year of the associated credit card
	 */
	private String exp_year;
	
	/**
	 * The zip code of the associated member
	 */
	private String zipCode;
	
	/**
	 * The randomized amount to be charged to a member's credit card
	 */
	private int amount;
	
	/**
	 * Flag indicating whether this card is banned or not (possible if "Referred for Deletion" by owner)
	 */
	private boolean isBanned;
	
	/**
	 * The Stripe Id of the member
	 */
	private String stripeId;
	
	/**
	 * The Customer object storing member information
	 */
	private Customer member;
	
	/**
	 * Creates a credit card object given the appropriate information
	 * @param email
	 * @param cardNumber
	 * @param zipCode
	 * @param cvv
	 * @param exp_month
	 * @param exp_year
	 */
	public StripeCreditCard(String email, 
							String cardNumber, 
							String zipCode, 
							String cvv, 
							String exp_month, 
							String exp_year) {
		Stripe.apiKey = "sk_test_gCabH088eiNoFnUbVBwfKCLV00p4slRZXy";
		this.email = email;
		this.cardNumber = cardNumber;
		this.zipCode = zipCode;
		this.cvv = cvv;
		this.exp_month = exp_month;
		this.exp_year = exp_year;
		this.isBanned = false; // default
		
		// Store the member in Stripe
    	Map<String, Object> customerParameters = new HashMap<String, Object>();
    	customerParameters.put("email", this.email);
    	try {
			Customer member = Customer.create(customerParameters);
			this.member = member;
			this.stripeId = member.getId();
		} catch (StripeException e) {
			// Generic error message
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
	 * @return a new Stripe Charge object storing the details of the charge (amount, customer, currency type)
	 */
	private Charge generateCharge() {
		// Generate random amount between 20-45 cents
		int amount = (int) (Math.random() * 20 + 50);
		this.amount = amount;
		
		Map<String, Object> chargeParameters = new HashMap<String, Object>();
    	chargeParameters.put("amount", Integer.toString(amount));
    	chargeParameters.put("currency", "usd");
    	chargeParameters.put("customer", member.getId());
    	try {
			return Charge.create(chargeParameters);
		} catch (StripeException e) {
			// Generic error message
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	/**
	 * Creates a Stripe Token object to store member credit card details (used in charging)
	 * @return a new Stripe Token object which stores a member's credit card details
	 */
	private Token createCard() {
    	Map<String, Object> cardParameters = new HashMap<String, Object>();
    	cardParameters.put("number", cardNumber);
    	cardParameters.put("exp_month", exp_month);
    	cardParameters.put("exp_year", exp_year);
    	cardParameters.put("cvc", cvv);
    	cardParameters.put("address_zip", zipCode);
    	
    	// Create a token which stores the details of the card so that we can charge it
    	Map<String, Object> tokenParameters = new HashMap<String, Object>();
    	tokenParameters.put("card", cardParameters);
    	
		try {
			return Token.create(tokenParameters);
		} catch (StripeException e) {
			// Generic error message
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
		
		// Check if null
		if (chargeTok == null)
			return false;
		
		// Link the credit card (Token) object to the customer
        Map<String, Object> source = new HashMap<String, Object>();
    	source.put("source", chargeTok.getId());
    	
    	try {
			member.getSources().create(source);
		} catch (StripeException e) {
			// Generic error message
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
		Map<String, Object> item = new HashMap<String, Object>();
		
		Map<String, Object> items = new HashMap<String, Object>();
		items.put("0", item);
		
		Map<String, Object> subscriptionParameters = new HashMap<String, Object>();
		subscriptionParameters.put("customer", member.getId());
		subscriptionParameters.put("items", item);
		
		try {
			Subscription.create(subscriptionParameters);
		} catch (StripeException e) {
			// Generic error message
		}
	}

	/**
	 * Verifies the validity of a credit card, and returns a Stripe ID if successfull, null if not
	 */
	@Override
	public String verify() {
		if (createCard() != null) {
			return stripeId;
		} else {
			return null;
		}
	}

	/**
	 * Return the Stripe ID of a specific member
	 */
	@Override
	public String getId() {
		return stripeId;
	}
	
}
