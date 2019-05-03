package stripe;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stripe.Stripe;
import com.stripe.exception.CardException;
import com.stripe.exception.StripeException;
import com.stripe.model.*;

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
	 * A String to store any potential Stripe error messages needed for output
	 */
	private String error;
	
	/**
	 * Boolean indicating whether we have linked a credit card to this member
	 */
	private boolean isLinked;

	/**
	 * Id of the customer subscription / FOR TESTING
	 */
	public String subscriptionId;
	
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
		this.isLinked = false; // default
		
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
	public double getAmount() {
		// Convert to dec
		String D = "0." + Integer.toString(amount);
		Double d = Double.valueOf(D);
		return d;
	}
	
	/**
	 * Creates a Stripe Charge object which stores the details of currency and amount charged to a customer
	 * @return a new Stripe Charge object storing the details of the charge (amount, customer, currency type)
	 */
	private Charge generateCharge() {
		// Generate random amount between 50-75 cents
		int amount = (int) (Math.random() * 25 + 50);
		this.amount = amount;
		
		Map<String, Object> chargeParameters = new HashMap<String, Object>();
    	chargeParameters.put("amount", Integer.toString(amount));
    	chargeParameters.put("currency", "usd");
    	chargeParameters.put("customer", member.getId());
    	try {
			return Charge.create(chargeParameters);
		} catch (StripeException e) {
			// Generic error message
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
		} catch (CardException e) {
			error = e.getCode();
			return null;
		} catch (StripeException e) {
			// Generic error message
			return null;
		}
	}
	
	/**
	 * Charge a member a randomized amount which will be verified, and return "" if the charge is successful
	 * @return a String value indicating a successful process, or an error if something is wrong
	 */
	@Override
	public String charge() {
		if (isBanned) { return "Credit card is banned"; }

		error = "";
		Token chargeTok = createCard();
		
		// Check if null
		if (chargeTok == null) {
			return error;
		}
		
		if (!isLinked) {
			// Link the credit card (Token) object to the customer do this ONCE per card
			// This ensures that we have ONE VALID linked credit card per member
	        Map<String, Object> source = new HashMap<String, Object>();
	    	source.put("source", chargeTok.getId());
	    	
	    	try {
				member.getSources().create(source);
			} catch (StripeException e) {
				// Generic error message
			}
	    	isLinked = true;
		}
		
		if (generateCharge() != null)
			return "";
		else {
			return "GENERIC STRIPE ERROR";
		}
	}

	/**
	 * Checks whether the credit card associated with the member is banned
	 * @return isBanned
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
		// Avoid enabling subscriptions on a banned card
		if (isBanned) { return; }
		if (on) {
			Map<String, Object> productParameters = new HashMap<>();
			productParameters.put("name", "membersOnly");

			Map<String, Object> planParams = new HashMap<String, Object>();
			planParams.put("amount", getAmount());
			planParams.put("interval", "month");
			planParams.put("product", "productParams");
			planParams.put("currency", "usd");

			try {
				Plan p = Plan.create(planParams);

				Map<String, Object> item = new HashMap<String, Object>();
				item.put("plan", "membersOnly");

				Map<String, Object> items = new HashMap<String, Object>();
				items.put("0", item);

				Map<String, Object> subscriptionParameters = new HashMap<String, Object>();
				subscriptionParameters.put("customer", member.getId());
				subscriptionParameters.put("items", item);

				try {
					Subscription s = Subscription.create(subscriptionParameters);
					subscriptionId = s.getId();
				} catch (StripeException e) {
					// Generic error message
				}

			} catch (StripeException e) {
				// Generic Stripe exception
			}
		} else {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("limit", 1);
			try {
				SubscriptionCollection subscriptions = Subscription.list(params);
				List<Subscription> subList = subscriptions.getData();
				for (Subscription s : subList) {
					s.cancel();
				}
				// Cancel any existing subscription
				subscriptionId = null;
			} catch(StripeException e) {
				// Generic Stripe exception
			}
		}
	}

	/**
	 * Cross verifies member "guess" charge to the actual Stripe charge amt
	 * @param guess the amount which the member guesses has been charged to his card
	 * @return a boolean indicating whether the member charge verification is successful or not
	 */
	@Override
	public boolean verifyCharge(double guess) {
		return guess == getAmount();
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