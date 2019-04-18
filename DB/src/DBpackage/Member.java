package DBpackage;

public class Member extends User{
	//Variables
	private String firstName;
	private String lastName;
	private String address1;
	private String address2;
	private String zipCode;
	private String state;
	private String stripeID;
	private int phoneNum;
	private int ccNum;
	private int ccSec;
	private int ccExpiryMonth;
	private int ccExpiryYear;
	private int points;
	
	//Constructor
	public Member(int ID, String email, String password, String type, String firstName, String lastName,
			String address1, String address2, String zipCode, String state, String stripeID, int phoneNum, int ccNum,
			int ccSec, int ccExpiryMonth, int ccExpiryYear, int points) {
		super(ID, email, password, type);
		this.firstName = firstName;
		this.lastName = lastName;
		this.address1 = address1;
		this.address2 = address2;
		this.zipCode = zipCode;
		this.state = state;
		this.stripeID = stripeID;
		this.phoneNum = phoneNum;
		this.ccNum = ccNum;
		this.ccSec = ccSec;
		this.ccExpiryMonth = ccExpiryMonth;
		this.ccExpiryYear = ccExpiryYear;
		this.points = points;
	}
}