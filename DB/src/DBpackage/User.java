package DBpackage;

public class User {
	//Instance variables
	private String email;
	private String firstName;
	private String lastName;
	int points;
	int idKey;
	
	//Constructor
	public User(String email, String firstName, String lastName, int points, int idKey) {
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.points = points;
		this.idKey = idKey;
	}
}
