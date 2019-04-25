package user;

import org.junit.Test;

import stripe.*;

public class User {
	
	protected String email;
	protected String firstName;
	protected String lastName;
	protected CreditCard creditCard;
	protected String stripeCreditCardID;
	protected int points;
	protected User invitedBy;
	protected boolean loggedIn; // seems unecessary to me, included in meantime
	
	public User(String email,
				String firstName,
				String lastName,
				int points,
				User invitedBy) {
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.points = points;
		this.creditCard = new StripeCreditCard(email);
		this.stripeCreditCardID = creditCard.getId();
		this.invitedBy = invitedBy;
		this.loggedIn = false; // seems unecessary to me, included in meantime
	}
	
	// Feel this is unecessary
	public void changeLoggedInStatus() { this.loggedIn = !this.loggedIn; }
	
	/**
	 *  Increments User point total in the DB by either a positive
	 *  or negative amount (if points are removed).
	 *
	 *  @param  none 
	 *  @return void
	 */

	public void addPoints(int points) {
		
	}

	/**
	 *  Charges the User’s stored credit card by calling the 
	 *  adapter.
	 *
	 *  @param  none 
	 *  @return boolean indicating if it is successful
	 */

	public boolean chargeCreditCard() {
		return creditCard.charge();
	}

	/**
	 *  Returns a boolean if the data is stored in the fieldName
	 *  for the User’s specific credential to be updated in the DB.
	 *
	 *  @param  fieldName	String indicating which field in the DB
	 * 				the data should be stored in
	 *  @param	data		A generic Object that is the data to be
	 * 				stored in the DB field.
	 *  @return boolean indicating if it is successful
	 */

	public boolean updateAccountInfo(String fieldName, Object data) {
		return true;
	}

	/**
	 *  Returns an Object containing the data in the specified
	 *  field within the DB where the desired credential from the 
	 *  specific User is requested.
	 *
	 *  @param  fieldName	String indicating which field in the DB
	 * 				the data should pulled from
	 *  @return boolean indicating if it is successful
	 */

	public Object getRegistrationInfo(String fieldName) {
		return null;
	}

	/* User - Testing */
	
	@Test
	public void testCCCharging() {
	   // Calls CC adapter to charge User’s card 
	   // verifies if the card is charged
	}

	@Test
	public void testAddPoints() {
	   // Calls DB API to determine if User point 
	   // total reflects a specific number of points added
	}

	@Test
	public void testInfoUpdated() {
	   // Calls DB API to determine if User credentials 
	   // reflect a specific change as made by updateAccountInfo()
	}

}
