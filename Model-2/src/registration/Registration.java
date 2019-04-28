package registration;

//import org.junit.Test;
import stripe.CreditCard;
import stripe.StripeCreditCard;

public class Registration {
	private String firstName;
	private String lastName;
	private String address1;
	private String address2;
	private int phoneNumber;
	private String email;
	private int zipCode;
	private String choosePassword;
	private String verifyPassword;
	private int creditCardNumber;
	private int cvv;
	private int expirationMonth;
	private int expirationYear;

	public Registration(String firstName,
						String lastName,
						String address1,
						String address2,
						int phoneNumber,
						String email,
						int zipCode,
						String choosePassword,
						String verifyPassword,
						int creditCardNumber,
						int cvv,
						int expirationMonth,
						int expirationYear) {

		this.firstName = firstName;
		this.lastName = lastName;
		this.address1 = address1;
		this.address2 = address2;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.zipCode = zipCode;
		this.choosePassword = choosePassword;
		this.verifyPassword = verifyPassword;

		boolean verifyResult = this.verify();

		if (verifyResult) {
			//continue
		} else {
			return;
			//Do not run any of the below code. 
		}

		CreditCard card = new StripeCreditCard(email, creditCardNumber, zipCode, creditCardNumber, cvv, expirationMonth, expirationYear);
		boolean cc_verification_result = card.verify();

		if (cc_verification_result != null) {
			this.stripeID = cc_verification_result;
			storeData();
		} else {
			return;
			//If the credit card is invalid, handle this somehow...
			//DO NOT call storeData();
		}


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

	private static boolean verify() {
		boolean result = false;
		if (emailCheck()) result = true;
		if (passwordCheck()) result = true;
		//if (creditCardCheck()) result = true;
		return result;
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
		// verify that password is correct
		return true;
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


	/* Registration - Testing
	Null values           [eg. Value not provided]
	String provided       [eg. Field requires integer]
	String not supplied   [eg. Field requires a String]
	String password1, 2 not matching [eg. User misspelled password 2nd time]
	String email not unique in DB [eg. User tries to use another person’s email]
	Data not in DB [eg. DBAdapter returns false on storeData()]
	Values not realistically valid [eg. zip code is int, but too long]

	@Test
	public void
	
	@Test
	public void 
	
	
	@Test
	public void identityTester() {
	   // Checks if check() methods correctly identify
	   // like Strings and correctly reject unlike Strings
	}

	@Test
	public void testStoredData() {
	   // Checks with DB API to see if a new User is created
	   // with correct credentials after calling storeData()
	}

}
	*/