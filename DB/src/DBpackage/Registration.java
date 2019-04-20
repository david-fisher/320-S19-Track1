package DBpackage;

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
		this.creditCardNumber = creditCardNumber;
		this.cvv = cvv;
		this.expirationMonth = expirationMonth;
		this.expirationYear = expirationYear;
		
	}
}
