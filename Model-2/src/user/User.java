package user;

import stripe.*;
import db.*;

public class User {
	
	protected String email;
	protected String firstName;
	protected String lastName;
	protected CreditCard creditCard;
	protected String stripeCreditCardID;
	protected int points;
	protected User invitedBy;
	protected boolean isValidated; // Is User currently verified by CC?
	protected String type;
	
	public User(String email,
				String firstName,
				String lastName,
				int points,
				User invitedBy,
				String type) {
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.points = points;
		this.stripeCreditCardID = creditCard.getId();
		this.invitedBy = invitedBy;
		this.isValidated = false;
		this.type = type;
	}
	
	public boolean checkIfUserValid(double charge) {
		if(creditCard.verifyCharge(charge)) this.isValidated = true;
		return isValidated;
	}
	
	// Feel this is unecessary
	public void changeLoggedInStatus() { this.loggedIn = !this.loggedIn; }
	
	/**
	 *  Increments User point total in the DB by either a positive
	 *  or negative amount (if points are removed).
	 *
	 *  @param
	 *  @return void
	 */

	public void addPoints(int points) { // TODO this does not do what it's supposed to
		//DBAdapter.setUserPoints(this.email, points);
	}

	/**
	 *  Charges the User’s stored credit card by calling the 
	 *  adapter.
	 *
	 *  @param
	 *  @return boolean indicating if it is successful
	 */

	public String chargeCreditCard() {
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

	public String updateAccountInfo(String fieldName, Object data) { // TODO this does not do what it's supposed to
		return "No Error Occurred";
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

	public Object getRegistrationInfo(String fieldName) { // TODO this does not do what it's supposed to
		return null;
	}
}
