package user;

import post.*;
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

	public DBAdapter DB;

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
	 *  @param	points Integer representing the number of points to be
	 *                 incremented or decremented (using a negative number)
	 *  @return	void
	 */

	public void addPoints(int points) {
		DB.updateUser(email, "points", points);
	}

	/**
	 *  Charges the User’s stored credit card by calling the 
	 *  CC adapter
	 *
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
	 * 						the data should be stored in
	 *  @param	data		A generic Object that is the data to be
	 * 						stored in the DB field.
	 *  @return boolean indicating if it is successful
	 */

	public String updateAccountInfo(String fieldName, Object data) {
		if(fieldName.equals("birthday")) {
			//DB.setUserBirthday(this.email,data); // commented out in DBA
		} else if(fieldName.equals("card")) {
			//DB.setUserCard(this.email,data); // commented out in DBA
		} else if(fieldName.equals("privacy")) {
			//DB.setUserPrivacy(this.email,data); // commented out in DBA
		} else return (fieldName + "Account Information Unsuccessfully Updated To: " + data);

		return (fieldName + "Account Information Successfully Updated To: " + data);
	}

	/*
	 * Admin Functionality
	 */

	public String editPost(Post post, String text) {
		if(this.type.equals("admin")) {
			//DB.setPost(post.postID, text); // commented out in DBA
			return "Post successfully updated";
		} else return "Post unsuccessfully updated";
	}
}
