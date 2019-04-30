

public class User {
	
	protected String email;
	protected String firstName;
	protected String lastName;
	protected String type;
	protected CreditCard creditCard;
	protected String address;
	protected String city;
	protected String state;
	protected String zip;
	protected String ccNum;
	protected String ccv;
	protected String expM;
	protected String expY;
	protected String birthday;
	protected String stripeCreditCardID;
	protected int points;
	protected String phone;
	protected String password;
	protected String invitedBy;
	protected boolean hasInvited;
	protected boolean loggedIn; // seems unecessary to me, included in meantime
	protected boolean isValidated; // if user is currently verified by credit card
	protected boolean privacy;
	
	public User(String email,
				String firstName,
				String lastName,
				int points,
				String invitedBy,
				CreditCard cc) {
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.points = points;
		//this.creditCard = new StripeCreditCard(email);
		this.stripeCreditCardID = creditCard.getId();
		this.invitedBy = invitedBy;
		this.loggedIn = false; // seems unecessary to me, included in meantime
		this.isValidated = false; // placeholder, must charge credit card + make sure it's valid
	}
	
	public boolean checkIfUserValid(double charge) {
		//if(charge == DBAdapter.getCharge(User)) this.isValidated = true;
		return true;
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
		/* DBAdapter.setPoints(); */
	}

	/**
	 *  Charges the User’s stored credit card by calling the 
	 *  adapter.
	 *
	 *  @param
	 *  @return boolean indicating if it is successful
	 */

	public String chargeCreditCard() {
		creditCard.charge();
		return "No Problems Here";
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

	/* User - Testing */
}