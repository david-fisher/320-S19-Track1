package com.Model2;

//import org.junit.Test;
//import stripe.CreditCard;
//import stripe.StripeCreditCard;

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
	}

	/**
	 * Returns a boolean value indicating whether the credentials
	 * presented to the Registration class constructor from the
	 * login screen are confirmed. Specifically, confirmed means
	 * that there is no duplicate emails, and evokes the credit
	 * card processing class to ensure the proper credit card is
	 * used.
	 * <p>
	 * Calls three methods [emailCheck(), passwordCheck(),
	 * creditCardCheck()] to verify the 3 things we need to check:
	 * is the email address unique, is the password the same as the
	 * verifyPassword field, and is the credit card chargeable.
	 *
	 * @param none
	 * @return boolean indicating if all the checks pass
	 */
	public String verify() {

		if (emailCheck()){

		}else{
			return "Invalid email";
		}

		if (passwordCheck())
		{

		}else{
			return "Passwords don't match";
		}

		CreditCard card = new StripeCreditCard(this.email, this.creditCardNumber, this.zipCode , this.cvv, this.expirationMonth , this.expirationYear);
		String resultCardValidation = card.verify();

		if(resultCardValidation == null){
			return "Invalid credit card";
		}

		//Create a User object in the database.
		//NOTE: This user will still be invalid until they verify their charge.
		storeData();

		return "";
	}

	/**
	 * Returns a boolean value indicating whether the email is
	 * unique (as in there is no database record using this specific
	 * email.
	 *
	 * @param none
	 * @return boolean indicating if the checks pass
	 */

	private boolean emailCheck() {
		// verify that email is unique and correct
		return true;
	}

	/**
	 * Returns a boolean value indicating whether the password fields
	 * for password and verifyPassword are identical.
	 *
	 * @param none
	 * @return boolean indicating if the checks pass
	 */

	private boolean passwordCheck() {
		return this.choosePassword.equals(this.verifyPassword);
	}

	/**
	 * Sends the credentials to the database for new User
	 * creation and storage. Returns boolean indicating
	 * whether it is successful, as determined by the DB.
	 *
	 * @param none
	 * @return boolean indicating if the DB has stored the data
	 */

	private boolean storeData() {
		return true;
	}

}

