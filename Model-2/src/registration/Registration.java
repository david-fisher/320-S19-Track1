package registration;

import db.DBAdapter;
import stripe.CreditCard;
import stripe.StripeCreditCard;
import user.User;
import java.util.regex.*;

import java.awt.*;

public class Registration {
	private String firstName;
	private String lastName;
	private String address1;
	private String address2;
	private int phoneNumber;
	private String email;
	private String zipCode;
	private String choosePassword;
	private String verifyPassword;
	private String creditCardNumber;
	private String cvv;
	private String expirationMonth;
	private String expirationYear;
	private DBAdapter DB;
	private CreditCard card;

	public Registration(String firstName,
						String lastName,
						String address1,
						String address2,
						int phoneNumber,
						String email,
						String zipCode,
						String choosePassword,
						String verifyPassword,
						String creditCardNumber,
						String cvv,
						String expirationMonth,
						String expirationYear) {

		this.firstName = firstName;
		this.lastName = lastName;
		this.address1 = address1;
		this.address2 = address2;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.zipCode = zipCode;
		this.choosePassword = choosePassword;
		this.verifyPassword = verifyPassword;
		this.creditCardNumber = creditCardNumber;
		this.cvv = cvv;
		this.expirationMonth = expirationMonth;
		this.expirationYear = expirationYear;
		this.DB = new DBAdapter();
	}

	/**
	 * Returns a String value indicating whether the credentials
	 * presented to the Registration class constructor from the
	 * login screen are confirmed. Specifically, confirmed means
	 * that there is no duplicate emails, and evokes the credit
	 * card processing class to ensure the proper credit card is
	 * used.
	 *
	 * Calls three methods [emailCheck(), passwordCheck(),
	 * creditCardCheck()] to verify the 3 things we need to check:
	 * is the email address unique, is the password the same as the
	 * verifyPassword field, and is the credit card chargeable.
	 *
	 * @return String indicating if all the checks pass
	 */
	public String verify() {
		if(!emailCheck()) return "Invalid email or already in use";
		if(!passwordCheck()) return "Passwords don't match";
		if(!zipCheck()) return "Invalid zip code";



		CreditCard card = new StripeCreditCard(this.email, this.creditCardNumber, this.zipCode , this.cvv, this.expirationMonth , this.expirationYear);
		String resultCardValidation = card.verify();

		if(resultCardValidation == null){
			return "Invalid credit card";
		}else{
			this.card = card;
		}

		//Create a User object in the database.
		//NOTE: This user will still be invalid until they verify their charge.
		storeData();

		return ""; //Empty string is the success code according to Cole
	}

	/**
	 * Returns a boolean value indicating whether the email is
	 * unique (as in there is no database record using this specific
	 * email.
	 *
	 * @return boolean indicating if the checks pass
	 */

	private boolean emailCheck() {
		// verify that email is in proper format
		Pattern p = Pattern.compile("^(.+)@(.+)$"); // https://howtodoinjava.com/regex/java-regex-validate-email-address/
		Matcher m = p.matcher(this.email);
		boolean b = m.matches();
		if(b){
			//Go do the next check
		}else{
			return false;
		}

		// Check if this email is already use by calling DB.getUser():
		// If the provided email is not associated with a user (eg. returns null),
		// Then we know the email is unique
		if(null == DB.getUser(this.email) ){
			return false;
		}else {
			return true;
		}
	}

	/**
	 * Returns a boolean value indicating whether the password fields
	 * for password and verifyPassword are identical.
	 *
	 * @return boolean indicating if the checks pass
	 */

	private boolean passwordCheck()
	{
		return this.choosePassword.equals(this.verifyPassword);
	}

	private boolean zipCheck()
	{
		Pattern p = Pattern.compile("^[0-9]{5}(?:-[0-9]{4})?$"); //https://howtodoinjava.com/regex/java-regex-validate-us-postal-zip-codes/
		Matcher m = p.matcher(this.zipCode);
		boolean b = m.matches();
		if(b){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * Sends the credentials to the database for new User
	 * creation and storage. Returns boolean indicating
	 * whether it is successful, as determined by the DB.
	 *
	 * @return boolean indicating if the DB has stored the data
	 */

	private boolean storeData() {
		User newUser = new User(this.email, this.firstName, this.lastName, 0, null, "member", this.card);
		DB.createUser(newUser);
		return true;
	}

}

