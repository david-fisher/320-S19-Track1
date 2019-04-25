package stripe;

public interface CreditCard {
	public String getId();						// Return the Stripe ID of a member
	public String verify();						// Verifies that the credit card provided is valid, and returns the Stripe ID if successful
	public String charge();					    // Returns "" if we've successfully processed and charged a member's credit card
	public double getAmount();				    // Returns the randomized small amount we charge to each member for verification
	public boolean isBanned();			    	// Checks whether a credit card is banned from the system
	public void setBanned(boolean on);			// To be used if member is "Referred for Deletion" by owner
	public void setSubscription(boolean on);	// Enable subscription payments (3 month verifications)
	boolean verifyCharge(double guess);			// Function to enable member credit card charge verification
}
