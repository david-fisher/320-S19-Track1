

public class User {
	//Instance variables
	int ID;
	private String email;
	private String password;
	private String type;
		
	//Constructor
	public User(int ID, String email, String password, String type) {
		this.ID = ID;
		this.email = email;
		this.password = password;
		this.type = type;
	}
}
