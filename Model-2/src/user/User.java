package user;

import stripe.*;
import db.*;

public class User {

	public String email;
	public String firstName;
	public String lastName;
	public CreditCard creditCard;
	public String stripeCreditCardID;
	public int points;
	public User invitedBy;
	public boolean isValidated; // Is User currently verified by CC?
	public String type;

	//Additional fields made by DB team
	public String address;
	public String city;
	public String state;
	public String zip;
	public String ccNum;
	public String ccv;
	public String expM;
	public String expY;
	public String birthday;
	public String phone;
	public boolean hasInvited;
	public boolean privacy;
	public boolean loggedIn;
	public String password;
	DBAdapter DB;
	public User(String email,
				String firstName,
				String lastName,
				int points,
				User invitedBy,
				String type,
				CreditCard creditCard) {
		this.creditCard = creditCard;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.points = points;
		this.stripeCreditCardID = creditCard.getId();
		this.invitedBy = invitedBy;
		this.isValidated = false;
		this.type = type;
		DB = new DBAdapter();
		this.addUserToDB();
	}

	public void addUserToDB() {
		if(DB.getUser(this.email) == null ) DB.createUser(this);
	}
	
	public boolean checkIfUserValid(double charge) {
		if(creditCard.verifyCharge(charge)) this.isValidated = true;
		return isValidated;
	}
	
	/**
	 *  Increments User point total in the DB by either a positive
	 *  or negative amount (if points are removed).
	 *
	 *  @param
	 *  @return void
	 */

	public void addPoints(int points) {
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
}
