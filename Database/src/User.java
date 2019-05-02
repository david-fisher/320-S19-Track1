

public class User {
	
	protected String email;
	protected String firstName;
	protected String lastName;
	protected String type;
	//protected CreditCard creditCard;
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
				String invitedBy) {
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.points = points;
		//this.creditCard = new StripeCreditCard(email);
		//this.stripeCreditCardID = creditCard.getId();
		this.invitedBy = invitedBy;
		//this.loggedIn = false; // seems unecessary to me, included in meantime
		//this.isValidated = false; // placeholder, must charge credit card + make sure it's valid
	}
	
	/* User - Testing */
}